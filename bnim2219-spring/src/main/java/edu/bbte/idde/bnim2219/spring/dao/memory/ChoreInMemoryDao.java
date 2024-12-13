package edu.bbte.idde.bnim2219.spring.dao.memory;

import edu.bbte.idde.bnim2219.spring.dao.ChoreDao;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.spring.model.Chore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

// implementation where chores are stored in memory
@Repository
@Profile("!jdbc")
@Slf4j
public class ChoreInMemoryDao extends MemoryDao<Chore> implements ChoreDao {

    @Override
    public Collection<Chore> findChoresByDone(Boolean done) throws BackendConnectionException {
        log.info("Found all chores, filtered by done");
        return entities.values()
                .stream()
                .filter(chore -> chore.getDone().equals(done))
                .collect(Collectors.toList());
    }
}
