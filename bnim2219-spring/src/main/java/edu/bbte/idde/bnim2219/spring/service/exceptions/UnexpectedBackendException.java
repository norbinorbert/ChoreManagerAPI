package edu.bbte.idde.bnim2219.spring.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnexpectedBackendException extends Exception {
    public UnexpectedBackendException(Throwable cause) {
        super(cause);
    }
}
