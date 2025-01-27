package edu.bbte.idde.bnim2219.backend.dao.memory;

import edu.bbte.idde.bnim2219.backend.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.backend.model.Chore;
import edu.bbte.idde.bnim2219.backend.dao.ChoreDao;

import java.util.Collection;

// implementation where chores are stored in memory
public class ChoreInMemoryDao extends MemoryDao<Chore> implements ChoreDao {
    @Override
    public Collection<Chore> findByMinMax(Integer min, Integer max) throws BackendConnectionException {
        return entities.values().stream()
                .filter(chore -> chore.getPriorityLevel() >= min && chore.getPriorityLevel() <= max).toList();
    }
}
