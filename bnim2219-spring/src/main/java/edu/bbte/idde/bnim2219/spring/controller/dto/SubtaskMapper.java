package edu.bbte.idde.bnim2219.spring.controller.dto;

import edu.bbte.idde.bnim2219.spring.controller.dto.incoming.NewSubtaskDTO;
import edu.bbte.idde.bnim2219.spring.model.Subtask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class SubtaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "chore", ignore = true)
    public abstract Subtask newSubtaskDTOtoSubtask(NewSubtaskDTO newSubtaskDTO);
}
