package filters;

import config.Constants;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Authentication implements Filter {
    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (authenticate(this.context, req,res)) {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/login.jsp");
        }
    }

    public static boolean authenticate(ServletContext context, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            if (context != null) {
                context.log("Unauthorized access request");
            }
            return false;
        } else if (!session.getAttributeNames().hasMoreElements() ||
                session.getAttribute(Constants.USER_NAME) == null ||
                session.getAttribute(Constants.TENANT_ID) == null ||
                session.getAttribute(Constants.ADMIN_ID) == null) {
            session.invalidate();
            if (context != null) {
                context.log("Unauthorized access request");
            }
            return false;
        } else {
            return true;
        }

    }

    public void destroy() {
        //close any resources here
    }
}
