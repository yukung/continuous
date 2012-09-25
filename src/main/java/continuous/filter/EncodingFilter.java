package continuous.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * リクエストのエンコーディングを設定するためのフィルタです.
 * <p>
 * 初期化パラメータ {@value #ENCODING}，{@value #FORCE_ENCODING} でリクエストの文字エンコーディングを指定します.
 * </p>
 * <p>
 * 初期化パラメータ {@value #URI_ENCODING}、{@value #URI_BYTES_ENCODING} で
 * {@link HttpServletRequest#getServletPath()}、
 * {@link HttpServletRequest#getPathInfo()} で取得できるパスのエンコーディングを指定します.
 * </p>
 * <p>
 * <table>
 * <thead>
 * <tr>
 * <th>param-name</th>
 * <th>param-value</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>{@value #ENCODING}</td>
 * <td>要求のエンコーディングを指定します.要求のエンコーディングが <code>null</code> か、
 * {@value #FORCE_ENCODING} に <code>true</code> が指定された場合はこのエンコーディングが要求に設定されます.</td>
 * </tr>
 * <tr>
 * <td>{@value #FORCE_ENCODING}</td>
 * <td><code>true</code> を指定した場合は、要求にエンコーディングが設定されていても {@value #ENCODING}
 * で上書きします.
 * </tr>
 * <tr>
 * <td>{@value #URI_ENCODING}</td>
 * <td>URI のエンコーディングを指定します.</td>
 * </tr>
 * <tr>
 * <td>{@value #URI_BYTES_ENCODING}</td>
 * <td>URI をバイト配列として取得する際のエンコーディングを指定します.</td>
 * </tr>
 * </tbody>
 * </table>
 * <caption>初期化パラメータ</caption>
 * </p>
 */
public class EncodingFilter implements Filter {
	
	/**
	 * HttpServletRequestWrapperをエンコーディング処理用にラップする，内部クラスです.
	 * 
	 * @author yukung
	 */
	private static class EncodingHttpServletRequestWrapper extends HttpServletRequestWrapper {
		
		/** URI エンコーディング */
		private final String uriEncoding;
		
		/** URI バイト列のエンコーディング */
		private final String uriBytesEncoding;
		
		
		/**
		﻿ * 指定されたリクエストをラップします.
		﻿ ﻿*
		﻿ ﻿* @param request
		﻿ ﻿*            リクエスト
		﻿ * @param uriEncoding
		﻿ *            URI エンコーディング
		﻿ * @param uriBytesEncoding
		﻿ *            URI バイト列のエンコーディング
		﻿ * @throws IOException
		﻿ *             スーパクラスのコンストラクタで例外が発生した場合
		﻿ */
		public EncodingHttpServletRequestWrapper(final HttpServletRequest request, final String uriEncoding,
				final String uriBytesEncoding) throws IOException {
			super(request);
			this.uriEncoding = uriEncoding;
			this.uriBytesEncoding = uriBytesEncoding;
		}
		
		@Override
		public String getPathInfo() {
			return rebuild(super.getPathInfo(), uriEncoding, uriBytesEncoding);
		}
		
		@Override
		public String getServletPath() {
			return rebuild(super.getServletPath(), uriEncoding, uriBytesEncoding);
		}
		
		/**
		 * 指定された文字列を一度 <code>bytesEncoding</code> でバイト配列に戻し，
		﻿ * <code>encoding</code> で文字列を再構築します.
		﻿ *
		﻿ * @param str
		﻿ *            文字列
		﻿ * @param encoding
		﻿ *            エンコーディング
		﻿ * @param bytesEncoding
		﻿ *            バイト配列に戻す時のエンコーディング
		﻿ * @return 再構築された文字列
		 */
		private String rebuild(final String str, final String encoding, final String bytesEncoding) {
			if (str == null || encoding == null) {
				return null;
			}
			try {
				return new String(str.getBytes(bytesEncoding), encoding);
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException(e);
			}
		}
	}
	
	
	private static final String ALREADY_FILTERD_ATTRIBUTE_NAME = EncodingFilter.class.getName() + ".FILTERED";
	
	/** エンコーディングのキー. */
	public static final String ENCODING = "encoding";
	
	/** 強制エンコーディングのキー. */
	public static final String FORCE_ENCODING = "forceEncoding";
	
	/** 強制エンコーディングのキー. */
	public static final String URI_ENCODING = "uriEncoding";
	
	/** URI エンコーディングのキー. */
	public static final String URI_BYTES_ENCODING = "uriBytesEncoding";
	
	/** URI バイト列のエンコーディングのキー. */
	public static final String DEFAULT_URI_BYTE_ENCODING = "ISO-8859-1";
	
	/** エンコーディング. */
	private String encoding;
	
	/** 強制エンコーディング設定. */
	private boolean forceEncoding = false;
	
	/** URI エンコーディング. */
	private String uriEncoding;
	
	/** URI バイト列のエンコーディング. */
	private String uriBytesEncoding;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (request.getAttribute(ALREADY_FILTERD_ATTRIBUTE_NAME) == null) {
			request.setAttribute(ALREADY_FILTERD_ATTRIBUTE_NAME, Boolean.FALSE);
			if (request.getCharacterEncoding() == null || forceEncoding) {
				request.setCharacterEncoding(encoding);
			}
			if (uriEncoding == null) {
				chain.doFilter(request, response);
			} else {
				final ServletRequest wrapper =
						new EncodingHttpServletRequestWrapper((HttpServletRequest) request, uriEncoding,
								uriBytesEncoding);
				chain.doFilter(wrapper, response);
			}
			request.removeAttribute(ALREADY_FILTERD_ATTRIBUTE_NAME);
		} else {
			chain.doFilter(request, response);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter(ENCODING);
		try {
			validateEncoding(encoding);
		} catch (final UnsupportedEncodingException e) {
			throw new ServletException(e);
		}
		
		final String forceEncodingString = config.getInitParameter(FORCE_ENCODING);
		if (forceEncodingString != null) {
			forceEncoding = Boolean.parseBoolean(forceEncodingString);
		}
		
		uriEncoding = config.getInitParameter(URI_ENCODING);
		try {
			validateEncoding(uriEncoding);
		} catch (UnsupportedEncodingException e) {
			throw new ServletException(e);
		}
		
		uriBytesEncoding = config.getInitParameter(URI_BYTES_ENCODING);
		if (uriBytesEncoding == null) {
			uriBytesEncoding = DEFAULT_URI_BYTE_ENCODING;
		}
		try {
			validateEncoding(uriBytesEncoding);
		} catch (UnsupportedEncodingException e) {
			throw new ServletException(e);
		}
	}
	
	/**
	 * 指定されたエンコーディングがサポートされているか検査します.
	 * 
	 * @param encoding
	 *            エンコーディング
	 * @throws UnsupportedEncodingException
	 *             指定されたエンコーディングがサポートされていない場合
	 */
	private void validateEncoding(String encoding) throws UnsupportedEncodingException {
		if (encoding != null && !Charset.isSupported(encoding)) {
			throw new UnsupportedEncodingException(encoding);
		}
	}
	
}
