package edu.bbte.idde.bnim2219.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bbte.idde.bnim2219.backend.model.Chore;
import edu.bbte.idde.bnim2219.backend.service.ChoreService;
import edu.bbte.idde.bnim2219.backend.service.exceptions.ChoreProcessingException;
import edu.bbte.idde.bnim2219.backend.service.exceptions.UnexpectedBackendException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/chores")
public class JsonServlet extends HttpServlet {
    private transient ObjectMapper objectMapper;
    private transient ChoreService choreService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
        choreService = new ChoreService();
    }

    //TODO: find out why requests trigger twice from postman

    // returns a chore if id parameter was provided, otherwise returns all chores
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setHeader("Content-Type", "application/json");
            String param = req.getParameter("id");
            if (param != null) {
                var chore = choreService.findById(Long.parseLong(param));
                objectMapper.writeValue(resp.getOutputStream(), chore);
                return;
            }
            var chores = choreService.findAll();
            objectMapper.writeValue(resp.getOutputStream(), chores);
        } catch (UnexpectedBackendException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ChoreProcessingException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Chore not found"));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Bad format for ID"));
        }
    }

    // creates a new chore based on the data given in the body
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setHeader("Content-Type", "application/json");
            String title = req.getParameter("title");
            if (title == null || title.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Please provide a title"));
                return;
            }
            String description = req.getParameter("description");
            Date date = Date.valueOf(req.getParameter("date"));
            Integer priorityLevel = Integer.valueOf(req.getParameter("priorityLevel"));
            Chore chore = new Chore(title, description, date, priorityLevel, false);
            Long id = choreService.create(chore);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Created new chore with id of " + id));
        } catch (UnexpectedBackendException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Bad priority level format. Expected an integer"));
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Bad date format. Expected format: yyyy-mm-dd"));
        }
    }

    // deletes the chore with the given id
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setHeader("Content-Type", "application/json");
            choreService.delete(Long.parseLong(req.getParameter("id")));
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Deleted chore"));
        } catch (UnexpectedBackendException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ChoreProcessingException e) {
            resp.setStatus(HttpServletResponse.SC_GONE);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Chore already deleted or didn't exist"));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Bad format for ID"));
        }
    }

    //updates chore that has the provided id, uses given parameters as new values
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = null;
        //TODO: find out how to get body parameters
        try {
            resp.setHeader("Content-Type", "application/json");
            id = Long.parseLong(req.getParameter("id"));
            String title = req.getParameter("title");
            if (title == null || title.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Please provide a title"));
                return;
            }
            String description = req.getParameter("description");
            Date date = Date.valueOf(req.getParameter("date"));
            Integer priorityLevel = Integer.valueOf(req.getParameter("priorityLevel"));
            Boolean done = Boolean.valueOf(req.getParameter("done"));
            Chore chore = new Chore(title, description, date, priorityLevel, done);
            choreService.update(id, chore);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Updated chore with id of " + id));
        } catch (UnexpectedBackendException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Bad id or priority level format. Expected an integer"));
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Bad date format. Expected format: yyyy-mm-dd"));
        } catch (ChoreProcessingException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Chore with id of " + id + " doesn't exist"));
        }
    }
}
