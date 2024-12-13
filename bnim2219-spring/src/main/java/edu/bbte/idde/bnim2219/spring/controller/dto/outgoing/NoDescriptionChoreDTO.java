package edu.bbte.idde.bnim2219.spring.controller.dto.outgoing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoDescriptionChoreDTO {
    private Long id;
    private String title;
    private Date deadline;
    private Integer priorityLevel;
    private Boolean done;
}
