package edu.bbte.idde.bnim2219.spring.controller;

import edu.bbte.idde.bnim2219.spring.controller.dto.outgoing.ChoreDTO;
import edu.bbte.idde.bnim2219.spring.controller.exception.InvalidFullException;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.ChoreNotFoundException;
import edu.bbte.idde.bnim2219.spring.model.Chore;
import edu.bbte.idde.bnim2219.spring.controller.dto.ChoreMapper;
import edu.bbte.idde.bnim2219.spring.controller.dto.incoming.NewChoreDTO;
import edu.bbte.idde.bnim2219.spring.controller.dto.incoming.UpdateChoreDTO;
import edu.bbte.idde.bnim2219.spring.controller.dto.outgoing.NoDescriptionChoreDTO;
import edu.bbte.idde.bnim2219.spring.service.ChoreService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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
    public Collection<NoDescriptionChoreDTO> findAll(@PathParam("done") Boolean done)
            throws BackendConnectionException {
        if (done != null) {
            return choreMapper.removeDescriptionFromChores(service.findChoresByDone(done));
        }
        return choreMapper.removeDescriptionFromChores(service.findAll());
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable("id") Long id,
                           @RequestParam(value = "full", required = true) String full)
            throws BackendConnectionException, ChoreNotFoundException, InvalidFullException {
        if ("no".equals(full)) {
            return choreMapper.removeDescriptionFromChore(service.findById(id));
        } else if ("yes".equals(full)) {
            return choreMapper.choreToChoreDTO(service.findById(id));
        } else {
            throw new InvalidFullException();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ChoreDTO> create(@RequestBody @Valid NewChoreDTO newChoreDTO)
            throws BackendConnectionException {
        Chore chore = choreMapper.newChoreDTOtoChore(newChoreDTO);
        chore.setDone(false);
        Long id = service.create(chore);
        chore.setId(id);
        URI createUri = URI.create("/chores/" + id);
        return ResponseEntity.created(createUri).body(choreMapper.choreToChoreDTO(chore));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @RequestBody @Valid UpdateChoreDTO updateChoreDTO)
            throws BackendConnectionException, ChoreNotFoundException {
        service.update(id, choreMapper.updateChoreDTOtoChore(updateChoreDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws BackendConnectionException, ChoreNotFoundException {
        service.delete(id);
    }
}
