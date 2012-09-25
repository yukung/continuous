package continuous.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		List<String> messages = new ArrayList<String>(Arrays.asList(new String[] {
			"ほげほげ",
			"ふがふが",
			"あばばばば"
		}));
		request.setAttribute("msgs", messages);
		return forwardPath("login.jsp");
	}
	
}
