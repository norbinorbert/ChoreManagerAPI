package edu.bbte.idde.bnim2219.spring.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewChoreDTO {
    @NotNull
    private String title;
    private String description;
    @NotNull
    private Date deadline;
    @NotNull
    @Positive
    private Integer priorityLevel;
}
