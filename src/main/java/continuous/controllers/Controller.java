package continuous.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * コントローラの基底クラスです. 一つのURLに対して一つのコントローラが対応します.
 * 
 * <p>コントローラを定義する際はこのクラスを継承して作成してください.
 * 
 * @author yukung
 */
public abstract class Controller {
	
	/** JSPファイルのプレフィックス */
	private static final String PREFIX_VIEW = "/WEB-INF/view/";
	
	/** サーブレットインスタンス */
	protected HttpServlet servlet = null;
	
	/** リダイレクトかフォワードか（true の場合はリダイレクトする） */
	public boolean isRedirect;
	
	
	/**
	 * 業務処理のエントリポイントとなるメソッドです.
	 * <p>
	 * このメソッドをオーバーライドして実際の処理を記述してください.
	 * </p>
	 * 
	 * @param request HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @return 遷移先のパス
	 * @throws ServletException
	 * @throws IOException
	 */
	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException;
	
	/**
	 * サーブレットのインスタンスを返します.
	 * 
	 * @return サーブレットインスタンス
	 */
	public HttpServlet getServlet() {
		return (servlet);
	}
	
	/**
	 * サーブレットのインスタンスを設定します.
	 * 
	 * @param servlet サーブレットインスタンス
	 */
	public void setServlet(final HttpServlet servlet) {
		this.servlet = servlet;
	}
	
	/**
	 * 遷移先のJSPのパスを返します.
	 * 
	 * @param jspName 遷移先JSP
	 * @return 遷移先JSPのパス
	 */
	protected String forwardPath(String jspName) {
		return PREFIX_VIEW + jspName;
	}
}
