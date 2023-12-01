package admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminInterface {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws IOException;
}
