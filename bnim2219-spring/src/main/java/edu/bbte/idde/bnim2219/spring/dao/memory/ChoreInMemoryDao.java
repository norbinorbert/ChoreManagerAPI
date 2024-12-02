package edu.bbte.idde.bnim2219.spring.dao.memory;

import edu.bbte.idde.bnim2219.spring.dao.ChoreDao;
import edu.bbte.idde.bnim2219.spring.model.Chore;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

// implementation where chores are stored in memory
@Repository
@Profile("mem")
public class ChoreInMemoryDao extends MemoryDao<Chore> implements ChoreDao {
}
