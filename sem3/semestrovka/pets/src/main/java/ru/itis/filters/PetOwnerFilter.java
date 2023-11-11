package ru.itis.filters;

import ru.itis.dao.UserPetDAO;
import ru.itis.utils.SessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/pet/*")
public class PetOwnerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // Получите информацию о текущем пользователе и питомце
        int userId = SessionManager.getUserFromSession(httpRequest).getId();

        int petId = Integer.parseInt(httpRequest.getPathInfo().substring(1));

        // Проверьте, является ли пользователь хозяином питомца
        boolean isPetOwner = UserPetDAO.isPetOwner(userId, petId);

        // Добавьте атрибут в запрос, указывающий, является ли пользователь хозяином питомца
        servletRequest.setAttribute("isPetOwner", isPetOwner);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
