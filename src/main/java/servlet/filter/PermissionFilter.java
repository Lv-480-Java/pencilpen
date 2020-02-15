package servlet.filter;


import domain.entity.User;
import servlet.entity.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PermissionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        UserDto user = (UserDto)session.getAttribute("user");

        if (user==null || !user.getUserRole().equals("ADMIN")) {
            request.setAttribute("text-result", "You have no admin permission");

            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/gallery.jsp");
            dispatcher.forward(request, response);
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
