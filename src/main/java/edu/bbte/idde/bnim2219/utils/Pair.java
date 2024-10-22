package edu.bbte.idde.bnim2219.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<F, S> {
    F first;
    S second;
}
