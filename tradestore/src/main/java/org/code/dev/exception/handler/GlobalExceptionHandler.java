package org.code.dev.exception.handler;

import org.code.dev.constants.Constant;
import org.code.dev.exception.ValidationException;
import org.code.dev.model.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetails> prepareException(ValidationException validationException) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder().type(Constant.BAD_REQUEST)
                                                                      .cause(Constant.VALIDATION_ERROR)
                                                                      .errorMessage(validationException.getMessage())
                                                                      .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

    }

}
