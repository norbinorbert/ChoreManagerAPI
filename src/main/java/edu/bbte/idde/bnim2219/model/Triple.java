package edu.bbte.idde.bnim2219.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Triple<L, M, R> {
    L left;
    M middle;
    R right;
}
