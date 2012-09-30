package continuous.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// ログインユーザ取得して、名前をセッションスコープに詰める
		
		// ログインユーザから、そのユーザのdone情報を取得する
		
		// カレンダー表示用のDTOに詰めて、requestスコープに詰める
		
		// ホーム画面にフォワード
		return forwardPath("login.jsp");
	}
	
}
