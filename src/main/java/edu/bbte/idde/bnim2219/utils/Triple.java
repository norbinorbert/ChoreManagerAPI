package edu.bbte.idde.bnim2219.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Triple<L, M, R> {
    L left;
    M middle;
    R right;
}
