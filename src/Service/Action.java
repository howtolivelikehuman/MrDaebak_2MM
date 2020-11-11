package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action{ //model 구현 시 execute()를 오버라이드해서 사용.
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}