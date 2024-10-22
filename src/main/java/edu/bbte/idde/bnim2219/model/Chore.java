package edu.bbte.idde.bnim2219.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Chore extends BaseEntity {
    private String title;
    private String description;
    private Date deadline;
    private Integer priorityLevel;
    private Boolean done;
}
