/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * User: tendaimupezeni
 * Date: 23/6/2023
 * Time: 22:07
 */

package com.example.springthymeleafecrude.AdviceExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(value = MaErrorMakuru.class)
    public ResponseEntity<ApiError> handleException(){

        ApiError apiError = new ApiError(400,"Ukuda kuita seii mdara",new Date());
        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST);
    }

}
