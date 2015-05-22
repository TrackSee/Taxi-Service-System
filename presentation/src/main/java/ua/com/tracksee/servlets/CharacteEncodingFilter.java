package ua.com.tracksee.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Implicitly using UTF-8 encoding for all client-server communication.
 *
 * @author Ruslan Gunavardana
 */
@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "*")
public class CharacteEncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
