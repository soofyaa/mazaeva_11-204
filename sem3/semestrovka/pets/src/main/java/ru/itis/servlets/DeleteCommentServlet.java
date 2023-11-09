package ru.itis.servlets;


import ru.itis.dao.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-comment")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int commentId = Integer.parseInt(req.getParameter("commentId"));
        int postId = Integer.parseInt(req.getParameter("postId"));
        CommentDAO.deleteCommentById(commentId);
        resp.sendRedirect("/petbook/post/" + postId);
    }
}
