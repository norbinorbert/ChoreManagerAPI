package edu.bbte.idde.bnim2219.spring.controller.dto;

import edu.bbte.idde.bnim2219.spring.controller.dto.incoming.NewChoreDTO;
import edu.bbte.idde.bnim2219.spring.controller.dto.incoming.UpdateChoreDTO;
import edu.bbte.idde.bnim2219.spring.controller.dto.outgoing.NoDescriptionChoreDTO;
import edu.bbte.idde.bnim2219.spring.controller.dto.outgoing.NoIdChoreDTO;
import edu.bbte.idde.bnim2219.spring.model.Chore;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ChoreMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "done", ignore = true)
    public abstract Chore newChoreDTOtoChore(NewChoreDTO newChoreDTO);

    @Mapping(target = "id", ignore = true)
    public abstract Chore updateChoreDTOtoChore(UpdateChoreDTO updateChoreDTO);

    public abstract NoIdChoreDTO removeIdFromChore(Chore chore);

    @IterableMapping(elementTargetType = NoDescriptionChoreDTO.class)
    public abstract Collection<NoDescriptionChoreDTO> removeDescriptionFromChores(Iterable<Chore> chores);
}
