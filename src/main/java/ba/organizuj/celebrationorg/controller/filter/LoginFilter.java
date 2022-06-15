package ba.organizuj.celebrationorg.controller.filter;

import ba.organizuj.celebrationorg.controller.login.UserSession;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * <li>1. jakarta.servlet.Filter </li>
 * <li>2. /dashboard/***</li>
 */
@WebFilter(urlPatterns = {"/dashboard/*"})
public class LoginFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        this.context.log("doFilter je pozvan");
        if (UserSession.USER.getFromSession(request) == null) {
            response.sendRedirect((request.getContextPath() + "/login"));
        } else {
            chain.doFilter(request, response);
        }
    }
}
