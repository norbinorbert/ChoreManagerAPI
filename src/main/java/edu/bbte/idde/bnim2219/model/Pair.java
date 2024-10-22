package edu.bbte.idde.bnim2219.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Pair<F, S> {
    F first;
    S second;
}
