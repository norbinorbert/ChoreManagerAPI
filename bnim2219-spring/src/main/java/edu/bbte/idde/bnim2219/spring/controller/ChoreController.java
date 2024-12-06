package edu.bbte.idde.bnim2219.spring.controller;

import edu.bbte.idde.bnim2219.spring.model.Chore;
import edu.bbte.idde.bnim2219.spring.model.ChoreMapper;
import edu.bbte.idde.bnim2219.spring.model.dto.incoming.NewChoreDTO;
import edu.bbte.idde.bnim2219.spring.model.dto.incoming.UpdateChoreDTO;
import edu.bbte.idde.bnim2219.spring.model.dto.outgoing.NoDescriptionChoreDTO;
import edu.bbte.idde.bnim2219.spring.model.dto.outgoing.NoIdChoreDTO;
import edu.bbte.idde.bnim2219.spring.service.ChoreService;
import edu.bbte.idde.bnim2219.spring.service.exceptions.ChoreProcessingException;
import edu.bbte.idde.bnim2219.spring.service.exceptions.UnexpectedBackendException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/chores")
public class ChoreController {
    @Autowired
    private ChoreService service;
    @Autowired
    private ChoreMapper choreMapper;

    @GetMapping
    public Collection<NoDescriptionChoreDTO> findAll() throws UnexpectedBackendException {
        Collection<Chore> chores = service.findAll();
        return choreMapper.removeDescriptionFromChores(chores);
    }

    @GetMapping("/{id}")
    public NoIdChoreDTO findById(@PathVariable("id") Long id)
            throws UnexpectedBackendException, ChoreProcessingException {
        return choreMapper.removeIdFromChore(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Chore> create(@RequestBody @Valid NewChoreDTO newChoreDTO) throws UnexpectedBackendException {
        Chore chore = choreMapper.newChoreDTOtoChore(newChoreDTO);
        Long id = service.create(chore);
        chore.setId(id);
        chore.setDone(false);
        URI createUri = URI.create("/books/" + id);
        return ResponseEntity.created(createUri).body(chore);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @RequestBody @Valid UpdateChoreDTO updateChoreDTO)
            throws UnexpectedBackendException, ChoreProcessingException {
        service.update(id, choreMapper.updateChoreDTOtoChore(updateChoreDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws UnexpectedBackendException, ChoreProcessingException {
        service.delete(id);
    }
}
