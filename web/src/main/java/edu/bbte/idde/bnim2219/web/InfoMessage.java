package edu.bbte.idde.bnim2219.web;

import lombok.AllArgsConstructor;
import lombok.Data;

// this will be sent in json format in case of user error or success
@Data
@AllArgsConstructor
public class InfoMessage {
    String message;
}
