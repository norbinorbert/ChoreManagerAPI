package edu.bbte.idde.bnim2219.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bbte.idde.bnim2219.backend.model.Chore;
import edu.bbte.idde.bnim2219.backend.service.ChoreService;
import edu.bbte.idde.bnim2219.backend.service.exceptions.ChoreProcessingException;
import edu.bbte.idde.bnim2219.backend.service.exceptions.UnexpectedBackendException;
import edu.bbte.idde.bnim2219.web.utils.InfoMessage;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/chores")
public class ChoreServlet extends HttpServlet {
    private transient ObjectMapper objectMapper;
    private transient ChoreService choreService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
        choreService = new ChoreService();
    }

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
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Unexpected error occurred. Please try again later"));
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
            Chore newChore = objectMapper.readValue(req.getReader(), Chore.class);
            String title = newChore.getTitle();
            Date deadline = newChore.getDeadline();
            Integer priorityLevel = newChore.getPriorityLevel();
            if (title == null || title.isEmpty() || deadline == null || priorityLevel == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Input parsing error. "
                        + "Please check if all necessary fields have been provided (title, deadline, priorityLevel)"));
                return;
            }
            String description = newChore.getDescription();
            Chore chore = new Chore(title, description, deadline, priorityLevel, false);
            Long id = choreService.create(chore);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Created new chore with id of " + id));
        } catch (UnexpectedBackendException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Unexpected error occurred. Please try again later"));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Bad priority level format. Expected an integer"));
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Bad date format. Expected format: yyyy-mm-dd"));
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Input parsing error. Please check if all necessary fields have been provided "
                            + "in the correct format (title - string, deadline - date, priorityLevel - integer)"));
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
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Unexpected error occurred. Please try again later"));
        } catch (ChoreProcessingException e) {
            resp.setStatus(HttpServletResponse.SC_GONE);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Chore already deleted or didn't exist"));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Bad format for ID"));
        }
    }

    private boolean isChoreBad(Chore chore) {
        return chore.getTitle() == null || chore.getTitle().isEmpty() || chore.getDeadline() == null
                || chore.getPriorityLevel() == null || chore.getDone() == null;
    }

    //updates chore that has the provided id, uses given parameters as new values
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = null;
        try {
            resp.setHeader("Content-Type", "application/json");
            id = Long.parseLong(req.getParameter("id"));
            Chore updateChore = objectMapper.readValue(req.getReader(), Chore.class);
            if (isChoreBad(updateChore)) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Input parsing error. Please check"
                        + "if all necessary fields have been provided (title, deadline, priorityLevel, done)"));
                return;
            }
            Chore chore = new Chore(updateChore.getTitle(), updateChore.getDescription(), updateChore.getDeadline(),
                    updateChore.getPriorityLevel(), updateChore.getDone());
            choreService.update(id, chore);
            objectMapper.writeValue(resp.getOutputStream(), new InfoMessage("Updated chore with id of " + id));
        } catch (UnexpectedBackendException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Unexpected error occurred. Please try again later"));
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
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getOutputStream(),
                    new InfoMessage("Input parsing error. Please check if all necessary fields have been provided "
                            + "in the correct format (title - string, deadline - date, priorityLevel - integer, "
                            + "done - boolean)"));
        }
    }
}
