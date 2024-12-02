package edu.bbte.idde.bnim2219.spring.controller;

import edu.bbte.idde.bnim2219.spring.model.Chore;
import edu.bbte.idde.bnim2219.spring.model.ChoreMapper;
import edu.bbte.idde.bnim2219.spring.model.dto.NewChoreDTO;
import edu.bbte.idde.bnim2219.spring.model.dto.UpdateChoreDTO;
import edu.bbte.idde.bnim2219.spring.service.ChoreService;
import edu.bbte.idde.bnim2219.spring.service.exceptions.ChoreProcessingException;
import edu.bbte.idde.bnim2219.spring.service.exceptions.UnexpectedBackendException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/chores")
public class ChoreController {
    @Autowired
    private ChoreService service;
    @Autowired
    private ChoreMapper choreMapper;

    @GetMapping
    public Collection<Chore> findAll() throws UnexpectedBackendException {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Chore findById(@PathVariable("id") Long id) throws UnexpectedBackendException, ChoreProcessingException {
        return service.findById(id);
    }

    @PostMapping
    public Long create(@RequestBody @Valid NewChoreDTO newChoreDTO) throws UnexpectedBackendException {
        return service.create(choreMapper.newChoreDTOtoChore(newChoreDTO));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid UpdateChoreDTO updateChoreDTO)
            throws UnexpectedBackendException, ChoreProcessingException {
        service.update(id, choreMapper.updateChoreDTOtoChore(updateChoreDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws UnexpectedBackendException, ChoreProcessingException {
        service.delete(id);
    }
}
