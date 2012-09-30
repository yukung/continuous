package continuous;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import continuous.controller.Controller;

/**
 * リクエスト及びレスポンスの制御を司るコントローラサーブレットです。
 *
 * @author yukung
 */
public class DispatcherServlet extends HttpServlet {
	
	/** コントローラクラスのパッケージプレフィックス */
	private static final String CONTROLLER_PACKAGE_PREFIX = "continuous.controller.";
	
	/** URL マッピング設定 */
	private static Properties routes = new Properties();
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * サーブレットを初期化します.
	 */
	@Override
	public void init() throws ServletException {
		loadRouting();
	}
	
	/**
	 * HTTP の GET メソッドで送られたリクエストを処理します.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	/**
	 * HTTP の POST メソッドで送られたリクエストを処理します.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doProcess(request, response);
	}
	
	/**
	 * コントローラクラスをリフレクションでインスタンス化します.
	 *
	 * @param controllerPath コントローラのパス
	 * @return コントローラクラス
	 * @throws ServletException 
	 * @since TODO
	 */
	private synchronized Controller createController(String controllerPath) throws ServletException {
		String controllerName = routes.getProperty(controllerPath);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Controller controller;
		try {
			controller = (Controller) classLoader.loadClass(CONTROLLER_PACKAGE_PREFIX + controllerName).newInstance();
		} catch (InstantiationException e) {
			throw new ServletException(e);
		} catch (IllegalAccessException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
		return controller;
	}
	
	/**
	 * リクエストURI を元に、対応するコントローラクラスを呼び出し、レスポンスを遷移先にディスパッチします.
	 *
	 * @param request HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException 
	 * @throws IOException
	 */
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String requestUri = request.getRequestURI();
		String contextPath = getServletContext().getContextPath();
		String controllerPath = formatURI(requestUri, contextPath);
		Controller controller = createController(controllerPath);
		String forward = controller.execute(request, response);
		if (controller.isRedirect) {
			response.sendRedirect(forward);
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
			requestDispatcher.forward(request, response);
		}
	}
	
	/**
	 * リクエストURIをコントローラにバインドする部分の文字列を抽出します.
	 *
	 * @param uri リクエストURI
	 * @param prefix リクエストパスのプレフィックス（コンテキストルート）
	 * @return コントローラパスのバインド文字列
	 */
	private String formatURI(String uri, String prefix) {
		if (!uri.startsWith(prefix)) {
			return null;
		}
		String path = uri.substring(prefix.length());
		int slash = path.lastIndexOf("/");
		int period = path.lastIndexOf(".");
		if ((period >= 0) && (period > slash)) {
			path = path.substring(0, period);
		}
		return path;
	}
	
	/**
	 * URL マッピング設定ファイルをロードします.
	 *
	 * @throws ServletException
	 */
	private void loadRouting() throws ServletException {
		InputStream inStream = getServletContext().getResourceAsStream(getServletConfig().getInitParameter("routes"));
		try {
			routes.load(inStream);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}
	
}
