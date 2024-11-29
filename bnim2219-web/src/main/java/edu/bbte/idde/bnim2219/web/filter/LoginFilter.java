package edu.bbte.idde.bnim2219.web.filter;

import edu.bbte.idde.bnim2219.web.servlet.LoginServlet;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// checks if user is logged in
@WebFilter("/index")
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        var session = req.getSession();
        if (!LoginServlet.loginSessionValue.equals(session.getAttribute(LoginServlet.loginSessionName))) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("login");
            return;
        }
        chain.doFilter(req, res);
    }
}
