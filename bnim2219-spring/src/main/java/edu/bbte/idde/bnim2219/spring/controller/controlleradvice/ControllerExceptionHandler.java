package edu.bbte.idde.bnim2219.spring.controller.controlleradvice;

import edu.bbte.idde.bnim2219.spring.controller.controlleradvice.utils.ErrorMessage;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.ChoreNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Stream;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final Stream<String> handleConstraintViolation(ConstraintViolationException e) {
        log.info("ConstraintViolationException occurred", e);
        return e.getConstraintViolations()
                .stream()
                .map(it -> it.getPropertyPath().toString() + it.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final Stream<String> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException occurred", e);
        return e.getBindingResult().getFieldErrors()
                .stream()
                .map(it -> it.getField() + " " + it.getDefaultMessage());
    }

    @ExceptionHandler(BackendConnectionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public final ErrorMessage handleBackendConnectionException(BackendConnectionException e) {
        log.info("BackendConnectionException occurred", e);
        return new ErrorMessage("Couldn't connect to the server");
    }

    @ExceptionHandler(ChoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public final ErrorMessage handleChoreNotFoundException(ChoreNotFoundException e) {
        log.info("ChoreNotFoundException occurred", e);
        return new ErrorMessage("Couldn't find chore because it likely doesn't exist");
    }
}
