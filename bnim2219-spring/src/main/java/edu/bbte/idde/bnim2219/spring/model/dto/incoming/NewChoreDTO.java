package edu.bbte.idde.bnim2219.spring.model.dto.incoming;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String title;
    private String description;
    @NotNull
    @FutureOrPresent
    private Date deadline;
    @NotNull
    @Positive
    private Integer priorityLevel;
}
