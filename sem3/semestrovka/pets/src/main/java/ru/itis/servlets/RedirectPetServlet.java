package ru.itis.servlets;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet("/pet/get-id/*")
//public class RedirectPetServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int petId = Integer.parseInt(req.getPathInfo().substring(1));
//        HttpSession session = req.getSession();
//        session.setAttribute("petId", petId);
//        resp.sendRedirect("/petbook/pet");
//    }
//}
