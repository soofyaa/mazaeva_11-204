package ru.itis;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class BlogServlet extends HttpServlet {
    private final ArrayList<String> blogPosts = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");

        StringBuilder blogHtml = new StringBuilder("<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Blog</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>My Blog</h1>");
        for (String post : blogPosts) {
            blogHtml.append("<p>").append(post).append("</p><br>");
        }
        blogHtml.append("</body></html>");

        response.getWriter().write(blogHtml.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String text = request.getParameter("text");
        blogPosts.add(text);

        response.sendRedirect("/servlets/blog");
    }
}
