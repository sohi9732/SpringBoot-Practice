package basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet", urlPatterns = {"/helloget"})
public class HelloServlet extends HttpServlet {
    @Override
    //HttpServletRequest : 요청에 대한 정보를 가지고 있음
    //HttpServletResponse : 브라우저에서 정보를 표현하기 위해 사용
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet 메소드 호출");
        resp.setCharacterEncoding("UTF-8");
        //response 객체에 printWriter의 인스턴스를 얻어서 HTML 내용을 출력한다.
        PrintWriter writer = resp.getWriter();
        //contentType 정의
        resp.setContentType("text/html");
        writer.println("<html>");
        writer.println("<head><title>또이블로그 Spring Boot</title></head>");
        writer.println("<body> get 요청 예제입니다. </body>");
        writer.println("</html>");
    }
}