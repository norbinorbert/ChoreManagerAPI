package edu.bbte.idde.bnim2219.spring.controller.dto;

import edu.bbte.idde.bnim2219.spring.controller.dto.incoming.NewSubtaskDTO;
import edu.bbte.idde.bnim2219.spring.controller.dto.outgoing.SubtaskDTO;
import edu.bbte.idde.bnim2219.spring.model.Subtask;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class SubtaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "chore", ignore = true)
    public abstract Subtask newSubtaskDTOtoSubtask(NewSubtaskDTO newSubtaskDTO);

    @IterableMapping(elementTargetType = SubtaskDTO.class)
    public abstract Collection<SubtaskDTO> subtaskToSubtaskDTO(Collection<Subtask> subtask);
}
