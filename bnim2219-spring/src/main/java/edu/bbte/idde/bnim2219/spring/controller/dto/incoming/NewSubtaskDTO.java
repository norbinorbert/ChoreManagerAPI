package edu.bbte.idde.bnim2219.spring.controller.dto.incoming;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewSubtaskDTO {
    @NotNull
    @NotEmpty
    @Length(max = 255)
    private String name;
}
