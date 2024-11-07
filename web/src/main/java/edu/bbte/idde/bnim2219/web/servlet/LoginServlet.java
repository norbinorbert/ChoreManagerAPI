package edu.bbte.idde.bnim2219.web.servlet;

import edu.bbte.idde.bnim2219.web.ThymeleafEngineFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// servlet for handling login form
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String defaultUsernameAndPassword = "admin";
    public static final String loginSessionName = "loginStatus";
    public static final String loginSessionValue = "loggedIn";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ThymeleafEngineFactory.buildEngine(getServletContext());
    }

    // provide the login page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ThymeleafEngineFactory.process(req, resp, "login.html", null);
    }

    // validate form input and redirect
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!defaultUsernameAndPassword.equals(username) || !defaultUsernameAndPassword.equals(password)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("message", "Invalid username or password");
            ThymeleafEngineFactory.process(req, resp, "login.html", map);
            return;
        }
        var session = req.getSession();
        session.setAttribute(loginSessionName, loginSessionValue);
        resp.sendRedirect("index");
    }
}
