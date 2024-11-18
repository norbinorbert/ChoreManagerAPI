package edu.bbte.idde.bnim2219.web.servlet;

import edu.bbte.idde.bnim2219.backend.service.ChoreService;
import edu.bbte.idde.bnim2219.backend.service.exceptions.UnexpectedBackendException;
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

// lists all chores in a table
@WebServlet("/index")
public class MainServlet extends HttpServlet {
    private transient ChoreService choreService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        choreService = new ChoreService();
        ThymeleafEngineFactory.buildEngine(getServletContext());
    }

    // uses thymeleaf to render items
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            var chores = choreService.findAll();
            Map<String, Object> model = new ConcurrentHashMap<>();
            model.put("chores", chores);
            ThymeleafEngineFactory.process(req, resp, "index.html", model);
        } catch (UnexpectedBackendException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, Object> model = new ConcurrentHashMap<>();
            model.put("message", "Unexpected error occurred. Please try again later");
            ThymeleafEngineFactory.process(req, resp, "error.html", model);
        }
    }

    // logging out
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var session = req.getSession();
        session.invalidate();
        resp.sendRedirect("index");
    }
}
