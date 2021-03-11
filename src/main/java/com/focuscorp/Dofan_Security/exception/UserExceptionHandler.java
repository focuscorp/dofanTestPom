package com.focuscorp.Dofan_Security.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Controller
@ControllerAdvice
public class UserExceptionHandler {

    private static final Logger logger = LogManager.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<UserError> noUserHandler(UserNotFoundException ex) {

        logger.info("noUserHandler() execution started");
        UserError error = new UserError();
        error.setErrCode(404);
        error.setDesc(ex.getMessage());
        error.setDate(new Date());
        logger.info("noUserHandler() execution finished");
        return new ResponseEntity<UserError>(error, HttpStatus.NOT_FOUND);

    }
}
