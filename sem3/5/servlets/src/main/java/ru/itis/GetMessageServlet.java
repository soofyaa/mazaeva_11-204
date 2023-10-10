package ru.itis;

import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getMessages")
public class GetMessageServlet extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String userName = req.getSession().getAttribute("userName").toString();
        if (userName == null) {
            resp.sendRedirect("/servlets/name");
            return;
        }

        PrintWriter out = resp.getWriter();

        out.println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Get Messages</title>
                </head>
                <body>
                <h1>Get your messages</h1>
                <ul>""");

        List<String> messages = Server.getServer().getMessages(userName);
        messages.forEach(message -> out.println("<li>" + message + "</li>"));

        out.println("""
                </ul>
                </body>
                </html>""");
    }

}
