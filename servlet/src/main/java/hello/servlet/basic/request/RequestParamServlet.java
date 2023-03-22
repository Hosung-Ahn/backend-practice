package hello.servlet.basic.request;

import org.apache.tomcat.util.net.openssl.OpenSSLUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        Enumeration<String> parameterNames = request.getParameterNames();
        parameterNames.asIterator().forEachRemaining(parameterName -> {
            System.out.println(parameterName + " " + request.getParameter(parameterName));
        });
        System.out.println("[전체 파라미터 조회] - end");

        System.out.println("[단일 파라미터 조회] - start");
        System.out.println("username : " + request.getParameter("username"));
        System.out.println("age : " + request.getParameter("age"));
        System.out.println("[단일 파라미터 조회] - end");

        System.out.println("[중복 파라미터 조회] - start");
        String[] names = request.getParameterValues("name");
        for (String name : names) {
            System.out.println("name : " + name);
        }
    }
}
