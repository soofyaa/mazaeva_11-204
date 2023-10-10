package ru.itis;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sendMessages")
public class SendMessageServlet extends HttpServlet {

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
                <h1>Send you messages</h1>
                                
                <form method="post">
                    <input name="recipient" type="text" placeholder="recipient's name">
                    <input name="message" type="text" placeholder="message">
                    <input name="send_button" type="submit">
                </form>
                </body>
                </html>""");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipient = req.getParameter("recipient");
        String message = req.getParameter("message");
        String userName = req.getSession().getAttribute("userName").toString();
        Server.getServer().sendMessage(recipient, message, userName);
    }
}