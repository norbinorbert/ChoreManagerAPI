package edu.bbte.idde.bnim2219.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bbte.idde.bnim2219.backend.service.ChoreService;
import edu.bbte.idde.bnim2219.backend.service.exceptions.UnexpectedBackendException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/chores")
public class JsonServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private ChoreService choreService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
        choreService = new ChoreService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var chores = choreService.findAll();
        } catch (UnexpectedBackendException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
