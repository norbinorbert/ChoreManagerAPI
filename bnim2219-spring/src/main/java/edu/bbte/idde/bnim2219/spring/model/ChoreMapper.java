package edu.bbte.idde.bnim2219.spring.model;

import edu.bbte.idde.bnim2219.spring.model.dto.NewChoreDTO;
import edu.bbte.idde.bnim2219.spring.model.dto.UpdateChoreDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ChoreMapper {
    public abstract Chore newChoreDTOtoChore(NewChoreDTO newChoreDTO);

    public abstract Chore updateChoreDTOtoChore(UpdateChoreDTO updateChoreDTO);
}
