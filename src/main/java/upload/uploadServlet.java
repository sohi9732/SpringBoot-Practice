package upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(urlPatterns = "/upload", name = "uploadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, //2mb
        maxFileSize = 1024 * 1024 * 10, //10mb
        maxRequestSize = 1024 * 1024 * 50, //50mb
        location = "/Users/또이/upload" //파일 저장 위치
)
public class uploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //경로
        final String path = request.getParameter("destination");
        //파일
        //실제 파일에 대한 정보는 doPost 메서드 블록 안에 request.getPart 메서드로 참조한다.
        final Part filePart = request.getPart("file");
        //파일 이름
        final String fileName = getFileName(filePart);
        final PrintWriter writer = response.getWriter();

        //request.getPart 메서드로 Part 객체 생성 후 getInputStream 메서드로 파일의 내용을 저장한다.
        try(OutputStream out = new FileOutputStream(new File(path + File.separator + fileName)); InputStream filecontent = filePart.getInputStream()) {
            int read = 0;
            final byte[] bytes = new byte[1024];

            while((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            writer.print("new File : " + fileName + "이 " + path + "에 생성되었습니다.");
        } catch (FileNotFoundException fne) {
            System.out.println(fne.getMessage());
        }
    }

    //헤더에 있는 파일 정보를 Part 객체에서 getHeader 메서드로 얻을 수 있다.
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("Part Header = {0}" + partHeader);
        for(String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
