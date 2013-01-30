package continuous.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * インデックスページのコントローラです.
 *
 * @since 0.0.1 for yukung
 * @version $Id$
 * @author yukung
 */
public class IndexContoller extends Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO リクエスト情報から、ユーザのログイン判定を行なって、ログインページへの遷移またはホーム画面への遷移処理を行う
		return null;
	}
	
}
