package edu.bbte.idde.bnim2219.spring.controller;

import edu.bbte.idde.bnim2219.spring.controller.dto.ChoreMapper;
import edu.bbte.idde.bnim2219.spring.controller.dto.SubtaskMapper;
import edu.bbte.idde.bnim2219.spring.controller.dto.incoming.NewSubtaskDTO;
import edu.bbte.idde.bnim2219.spring.controller.dto.outgoing.ChoreDTO;
import edu.bbte.idde.bnim2219.spring.controller.dto.outgoing.SubtaskDTO;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.ChoreNotFoundException;
import edu.bbte.idde.bnim2219.spring.model.Chore;
import edu.bbte.idde.bnim2219.spring.model.Subtask;
import edu.bbte.idde.bnim2219.spring.service.ChoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/chores/{id}/subtasks")
@Profile("jpa")
public class CombinedController {
    @Autowired
    private ChoreService choreService;
    @Autowired
    private ChoreMapper choreMapper;
    @Autowired
    private SubtaskMapper subtaskMapper;

    @GetMapping
    public Collection<SubtaskDTO> findAll(@PathVariable("id") Long id)
            throws BackendConnectionException, ChoreNotFoundException {
        return subtaskMapper.subtaskToSubtaskDTO(choreService.findById(id).getSubtasks());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChoreDTO addSubtask(@PathVariable("id") Long id, @RequestBody NewSubtaskDTO newSubtaskDTO)
            throws BackendConnectionException, ChoreNotFoundException {
        Chore chore = choreService.findById(id);
        Subtask subtask = subtaskMapper.newSubtaskDTOtoSubtask(newSubtaskDTO);
        subtask.setChore(chore);
        chore.getSubtasks().add(subtask);
        choreService.update(id, chore);
        return choreMapper.choreToChoreDTO(chore);
    }

    @DeleteMapping("/{subtaskId}")
    public void deleteSubtask(@PathVariable("id") Long id, @PathVariable("subtaskId") Long subtaskId)
            throws BackendConnectionException, ChoreNotFoundException {
        Chore chore = choreService.findById(id);
        chore.getSubtasks().removeIf(e -> e.getId().equals(subtaskId));
        choreService.update(id, chore);
    }
}
