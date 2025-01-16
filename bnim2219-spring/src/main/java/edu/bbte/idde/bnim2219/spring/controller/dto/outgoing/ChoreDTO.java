package edu.bbte.idde.bnim2219.spring.controller.dto.outgoing;

import lombok.*;

import java.sql.Date;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoreDTO {
    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private Integer priorityLevel;
    private Boolean done;
    private Collection<SubtaskDTO> subtasks;
}
