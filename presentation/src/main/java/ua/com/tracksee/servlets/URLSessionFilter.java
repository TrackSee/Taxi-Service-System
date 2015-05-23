package ua.com.tracksee.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

/**
 * Filters jSessionId in URLs due to security reasons.
 *
 * @author Ruslan Gunavardana
 */
@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "*")
public class URLSessionFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(httpResponse) {
            public String encodeRedirectURL(String url) {
                return url;
            }

            public String encodeURL(String url) {
                return url;
            }
        };
        chain.doFilter(request, wrappedResponse);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
