package edu.bbte.idde.bnim2219.spring.dao.jdbc;

import edu.bbte.idde.bnim2219.spring.dao.ChoreDao;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.ChoreNotFoundException;
import edu.bbte.idde.bnim2219.spring.model.Chore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

// implementation where chores are stored in database
@Slf4j
@Repository
@Profile("jdbc")
public class ChoreInJdbcDao extends JdbcDao<Chore> implements ChoreDao {

    // creates a new chore and returns its id
    @Override
    public synchronized Long create(Chore entity) throws BackendConnectionException {
        try (Connection connection = getConnection()) {
            log.info("Trying to insert a line into chores");
            String query = "insert into chores values (default, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement
                         = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, entity.getTitle());
                preparedStatement.setString(2, entity.getDescription());
                preparedStatement.setDate(3, entity.getDeadline());
                preparedStatement.setInt(4, entity.getPriorityLevel());
                preparedStatement.setBoolean(5, false);
                preparedStatement.executeUpdate();
                var keys = preparedStatement.getGeneratedKeys();
                log.info("Inserted a line into chores");
                if (keys.next()) {
                    return keys.getLong(1);
                } else {
                    return 0L;
                }
            }
        } catch (SQLException e) {
            log.error("Failed to insert line into chores due to server error: {}", e.getMessage());
            throw new BackendConnectionException(e);
        }
    }

    private Chore createChoreFromResultSet(ResultSet resultSet) throws SQLException {
        Chore chore = new Chore();
        chore.setId(resultSet.getLong("choreID"));
        chore.setTitle(resultSet.getString("title"));
        chore.setDescription(resultSet.getString("description"));
        chore.setDeadline(resultSet.getDate("deadline"));
        chore.setPriorityLevel(resultSet.getInt("priorityLevel"));
        chore.setDone(resultSet.getBoolean("done"));
        return chore;
    }

    // returns the chore with the provided ID
    @Override
    public Chore findById(Long id) throws BackendConnectionException, ChoreNotFoundException {
        try (Connection connection = getConnection()) {
            log.info("Trying to find specific chore");
            String query = "select * from chores where choreID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Chore chore = createChoreFromResultSet(resultSet);
                    log.info("Successfully found chore");
                    return chore;
                } else {
                    log.warn("Failed to find chore because it doesn't exist");
                    throw new ChoreNotFoundException();
                }
            }
        } catch (SQLException e) {
            log.error("Failed to find chore due to server error: {}", e.getMessage());
            throw new BackendConnectionException(e);
        }
    }

    // returns all chores in a collection
    @Override
    public Collection<Chore> findAll() throws BackendConnectionException {
        try (Connection connection = getConnection()) {
            log.info("Trying to find all chores");
            String query = "select * from chores";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                Collection<Chore> chores = new ArrayList<>();
                while (resultSet.next()) {
                    chores.add(createChoreFromResultSet(resultSet));
                }
                log.info("Successfully found all chores");
                return chores;
            }
        } catch (SQLException e) {
            log.error("Failed to find chores due to server error: {}", e.getMessage());
            throw new BackendConnectionException(e);
        }
    }

    // updates the chore that has the provided ID, uses data from the provided chore
    @Override
    public synchronized void update(Long id, Chore entity) throws BackendConnectionException, ChoreNotFoundException {
        try (Connection connection = getConnection()) {
            log.info("Trying to update chore");
            String query = "update chores set title = ?, description = ?, deadline = ?, priorityLevel = ?, "
                    + "done = ? where choreID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, entity.getTitle());
                preparedStatement.setString(2, entity.getDescription());
                preparedStatement.setDate(3, entity.getDeadline());
                preparedStatement.setInt(4, entity.getPriorityLevel());
                preparedStatement.setBoolean(5, entity.getDone());
                preparedStatement.setLong(6, id);
                if (preparedStatement.executeUpdate() == 0) {
                    log.warn("Couldn't update chore because it likely doesn't exist");
                    throw new ChoreNotFoundException();
                }
                log.info("Successfully updated chore");
            }
        } catch (SQLException e) {
            log.error("Failed to update chore due to server error: {}", e.getMessage());
            throw new BackendConnectionException(e);
        }
    }

    // deletes the chore that has the provided ID
    @Override
    public synchronized void delete(Long id) throws BackendConnectionException, ChoreNotFoundException {
        try (Connection connection = getConnection()) {
            log.info("Trying to delete chore");
            String query = "delete from chores where choreID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                if (preparedStatement.executeUpdate() == 0) {
                    log.warn("Couldn't delete chore because it likely doesn't exist");
                    throw new ChoreNotFoundException();
                }
                log.info("Successfully deleted chore");
            }
        } catch (SQLException e) {
            log.error("Failed to delete chore due to server error: {}", e.getMessage());
            throw new BackendConnectionException(e);
        }
    }

    @Override
    public Collection<Chore> findChoresByDone(Boolean done) throws BackendConnectionException {
        try (Connection connection = getConnection()) {
            log.info("Trying to find all chores, filtered by done");
            String query;
            if (done) {
                query = "select * from chores where done";
            } else {
                query = "select * from chores where not done";
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                Collection<Chore> chores = new ArrayList<>();
                while (resultSet.next()) {
                    chores.add(createChoreFromResultSet(resultSet));
                }
                log.info("Successfully found all chores, filtered by done");
                return chores;
            }
        } catch (SQLException e) {
            log.error("Failed to find chores filtered by done due to server error: {}", e.getMessage());
            throw new BackendConnectionException(e);
        }
    }
}
