package hello.servlet.web;

import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE html>");
        writer.write("<html>");
        writer.write("  <head>");
        writer.write("    <meta charset=\"UTF-8\">");
        writer.write("    <title>Title</title>");
        writer.write("  </head>");
        writer.write("  <body>");
        writer.write("    <form action=\"/servlet/members/save\" method=\"post\">");
        writer.write("      username: <input type=\"text\" name=\"username\" />");
        writer.write("      age: <input type=\"text\" name=\"age\" />");
        writer.write("      <button type=\"submit\">Save</button>");
        writer.write("    </form>");
        writer.write("  </body>");
        writer.write("</html>");
    }
}
