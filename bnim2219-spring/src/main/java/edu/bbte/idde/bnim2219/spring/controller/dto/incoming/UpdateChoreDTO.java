package edu.bbte.idde.bnim2219.spring.controller.dto.incoming;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChoreDTO {
    @NotNull
    @NotEmpty
    @Length(max = 50)
    private String title;
    @Length(max = 255)
    private String description;
    @NotNull
    private Date deadline;
    @NotNull
    @Positive
    private Integer priorityLevel;
    @NotNull
    private Boolean done;
}
