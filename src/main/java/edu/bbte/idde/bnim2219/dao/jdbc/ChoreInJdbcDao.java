package edu.bbte.idde.bnim2219.dao.jdbc;

import edu.bbte.idde.bnim2219.dao.ChoreDao;
import edu.bbte.idde.bnim2219.dao.exceptions.NotFoundException;
import edu.bbte.idde.bnim2219.model.Chore;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

// implementation where chores are stored in database
@Slf4j
public class ChoreInJdbcDao extends JdbcDao<Chore> implements ChoreDao {
    public ChoreInJdbcDao() {
        super();
    }
    
    @Override
    public Long create(Chore entity) {
        log.info("asd");
        return 0L;
    }

    @Override
    public Chore findById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public Collection<Chore> findAll() {
        return List.of();
    }

    @Override
    public void update(Long id, Chore entity) throws NotFoundException {

    }

    @Override
    public void delete(Long id) throws NotFoundException {

    }
}
