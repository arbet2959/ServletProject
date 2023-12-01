package friend;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FriendInterface {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws IOException;
}
