package filters;

import config.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// TODO: use some framework for support of role in the system. This solution is terrify :D

public class RoleAdmin implements Filter {
    private ServletContext context;
    private final String ADMIN_ROLE = "admin";

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean isAuthenticated = Authentication.authenticate(this.context, req, res);
        //checking whether the session exists
        if (isAuthenticated) {
            if (session.getAttribute(Constants.ROLE) == null || !((String) session.getAttribute(Constants.ROLE)).equals(ADMIN_ROLE)) {
                this.context.log("Unauthorized access request. No allowed role");
                //req.setAttribute("msg", Constants.ERR_NO_ALLOWED_ROLE);
                session.setAttribute("msg", Constants.ERR_NO_ALLOWED_ROLE);
                res.sendRedirect("/login.jsp");
            } else {
                // pass the request along the filter chain
                chain.doFilter(request, response);
            }
        }
    }

    public void destroy() {
        //close any resources here
    }
}
