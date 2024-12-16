package edu.bbte.idde.bnim2219.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Chore extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String title;
    private String description;
    @Column(nullable = false)
    private Date deadline;
    @Column(nullable = false)
    private Integer priorityLevel;
    @Column(nullable = false)
    private Boolean done;
}
