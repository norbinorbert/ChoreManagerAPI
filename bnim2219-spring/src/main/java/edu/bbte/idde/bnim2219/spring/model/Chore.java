package edu.bbte.idde.bnim2219.spring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Collection;

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
    @OneToMany(mappedBy = "chore", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Collection<Subtask> subtasks;
}
