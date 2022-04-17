package org.code.dev.exception.handler;

import org.code.dev.constants.Constant;
import org.code.dev.exception.ValidationException;
import org.code.dev.model.ExceptionDetails;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Optional;
import java.util.function.Function;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetails> prepareException(ValidationException validationException) {
        /*Optional<ResponseEntity<ExceptionDetails>> maturityExceptionDetails = Optional.ofNullable(httpStatusCodeException)
                                                                                      .filter(statusCode -> statusCode.getResponseBodyAsString()
                                                                                      .contains(Constant.MATURITY_ERROR_MSG))
                                                                                      .map(mapMaturityException);

        Optional<ResponseEntity<ExceptionDetails>> versionExceptionDetails = Optional.ofNullable(httpStatusCodeException)
                                                                                     .filter(statusCode -> statusCode.getResponseBodyAsString()
                                                                                     .contains(Constant.VERSION_ERROR_MSG))
                                                                                     .map(mapVersionException);

        ResponseEntity<ExceptionDetails> exceptionDetailsResponseEntity = maturityExceptionDetails.orElseGet(versionExceptionDetails::get);
        return exceptionDetailsResponseEntity;*/

        ExceptionDetails exceptionDetails = ExceptionDetails.builder().type(Constant.BAD_REQUEST)
                                                                      .cause(Constant.VALIDATION_ERROR)
                                                                      .errorMessage(validationException.getMessage())
                                                                      .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

    }

    private Function<HttpStatusCodeException, ResponseEntity<ExceptionDetails>> mapMaturityException = httpStatusCodeException -> {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder().type(Constant.BAD_REQUEST)
                                                                      .cause(Constant.VALIDATION_ERROR)
                                                                      .errorMessage(httpStatusCodeException.getMessage()
                                                                                                           .equalsIgnoreCase(
                                                                                                           Constant.MATURITY_ERROR_MSG) ?
                                                                                                           Constant.MATURITY_ERROR_MSG : "UNKNOWN ERROR")
                                                                      .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    };

    private Function<HttpStatusCodeException, ResponseEntity<ExceptionDetails>> mapVersionException = httpStatusCodeException -> {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder().type(Constant.BAD_REQUEST)
                                                                      .cause(Constant.VALIDATION_ERROR)
                                                                      .errorMessage(httpStatusCodeException.getMessage()
                                                                                                           .equalsIgnoreCase(
                                                                                                           Constant.VERSION_ERROR_MSG) ?
                                                                                                           Constant.VERSION_ERROR_MSG : "UNKNOWN ERROR")
                                                                      .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    };

}
