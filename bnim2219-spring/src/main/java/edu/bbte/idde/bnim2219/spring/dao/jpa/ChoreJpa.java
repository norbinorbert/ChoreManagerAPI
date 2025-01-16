package edu.bbte.idde.bnim2219.spring.dao.jpa;

import edu.bbte.idde.bnim2219.spring.model.Chore;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("jpa")
public interface ChoreJpa extends JpaRepository<Chore, Long> {
    Collection<Chore> findByDone(Boolean done);
}
