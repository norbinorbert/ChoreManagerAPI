package edu.bbte.idde.bnim2219.spring.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChoreProcessingException extends Exception {
    public ChoreProcessingException(Throwable cause) {
        super(cause);
    }
}
