package ru.itis;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");

        String formHtml = "<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Blog</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"POST\" action=\"/servlets/blog\">\n" +
                "    Your text: <input type=\"text\" name=\"text\">\n" +
                "    <input type=\"submit\" value=\"Add\">\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>";

        response.getWriter().write(formHtml);
    }
}
