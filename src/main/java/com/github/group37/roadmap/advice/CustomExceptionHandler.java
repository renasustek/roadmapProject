package com.github.group37.roadmap.advice;

import com.github.group37.roadmap.errors.CouldntCreateRoadmap;
import com.github.group37.roadmap.errors.RoadMapNotFoundException;
import com.github.group37.roadmap.errors.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RoadMapNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String roadmapNotFound(RoadMapNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CouldntCreateRoadmap.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String couldntCreateRoadmap(CouldntCreateRoadmap ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String transactionSystemException(TransactionSystemException ex) {
        return "Name or password either too long or short.";
    }
}