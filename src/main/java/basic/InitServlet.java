package basic;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//URL 매핑은 WebServlet 어노테이션(annotation)으로 작성한다.
//urlPatterns에 s는 복수형을 뜻한다. 하나의 서블릿을 다수의 URL에 매핑할 수 있다. 여러 개를 지정할 때는 콤마(,)로 구분해 설정한다.
@WebServlet(
        name = "initServlet", urlPatterns = {"/init"},
        initParams = {@WebInitParam(name = "siteName", value = "또이 블로그")}
)
//HttpServlet : 일반적으로 서블릿을 만들 때 상속받는 추상 클래스
public class InitServlet extends HttpServlet {
    private String myParam = "";

    //init 메서드는 초기화에 대응되는 메서드이므로 한 번만 호출된다. 페이지를 여러 번 호출해도 출력 결과가 반복되지 않는다.
    //init 메서드의 이런 성격을 이용해 초기화 시 파라미터를 전달하고 싶은 경우 servletConfig를 사용한다.
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init call");
        this.myParam = servletConfig.getInitParameter("siteName");
        System.out.println("입력받은 사이트 명은 " + myParam + "입니다.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hello");
    }
}
