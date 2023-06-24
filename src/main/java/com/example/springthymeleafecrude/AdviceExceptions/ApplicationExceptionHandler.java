/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * User: tendaimupezeni
 * Date: 23/6/2023
 * Time: 22:07
 */

package com.example.springthymeleafecrude.AdviceExceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String,String> errorMAp = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
        {
            errorMAp.put(error.getField(), error.getDefaultMessage());
        });
        return errorMAp;
    }


//    @ExceptionHandler(UserNotFoundException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Map<String,String> handlerBusinessExeption(UserNotFoundException exception){
//        Map<String,String> errorMap = new HashMap<>();
//        errorMap.put("errotMessage",exception.getMessage());
//
//        return  errorMap;
//
//    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof SQLIntegrityConstraintViolationException) {
            // Check if the cause of the exception is SQLIntegrityConstraintViolationException
            // Customize the error message as per your requirement
            return "redirect:/your-form-url?error=EmailAlreadyExists";
        }

        // Handle other exceptions or re-throw for default error handling
        throw ex;
    }


}
