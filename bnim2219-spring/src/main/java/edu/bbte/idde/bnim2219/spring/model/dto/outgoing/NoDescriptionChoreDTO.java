package edu.bbte.idde.bnim2219.spring.model.dto.outgoing;

import edu.bbte.idde.bnim2219.spring.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NoDescriptionChoreDTO extends BaseEntity {
    private String title;
    private Date deadline;
    private Integer priorityLevel;
    private Boolean done;
}
