package edu.bbte.idde.bnim2219.spring.model.dto.outgoing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoIdChoreDTO {
    private String title;
    private String description;
    private Date deadline;
    private Integer priorityLevel;
    private Boolean done;
}
