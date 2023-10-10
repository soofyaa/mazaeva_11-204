package ru.itis;

import lombok.SneakyThrows;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/name")
public class NameServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Name</title>
                </head>
                <body>
                <h1>Your name</h1>
                <form method="post">
                    <input name="name" type="text">
                    <input name="name_button" type="submit">
                </form>
                </body>
                </html>""");
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        HttpSession session = req.getSession();
        session.setAttribute("userName", name);
        Server.getServer().setUserName(name);
        resp.sendRedirect("/servlets/getMessages");
    }
}