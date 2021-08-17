package basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//폼(form) 태그의 action 속성값과 서블릿의 urlPatterns 값은 같아야 한다.(postsend)
@WebServlet(name = "LoginServlet", urlPatterns = {"/postsend"})
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost 메서드 호출");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        resp.setContentType("text/html");

        //getParameter 메서드로 아이디와 패스워드를 전달받는다.
        //폼(form)에서 만든 input 필드의 name 속성값과 getParameter 메서드의 파라미터는 같아야 한다.(user, pwd)
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");
        writer.println("<html>");
        writer.println("<head><title>Login Servlet</title></head>");
        writer.println("<body>");
        writer.println("전달받은 이름은 " + user + "이고" + "<br/>" + "비밀번호는 " + pwd + "입니다.");
        writer.println("</body>");
        writer.println("</html>");
    }
}
