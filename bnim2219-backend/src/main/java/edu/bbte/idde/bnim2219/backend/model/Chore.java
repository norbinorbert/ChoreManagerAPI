package edu.bbte.idde.bnim2219.backend.model;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Chore extends BaseEntity {
    private String title;
    private String description;
    private Date deadline;
    private Integer priorityLevel;
    private Boolean done;
}
