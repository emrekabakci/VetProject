package com.emre.vetproject.core.config;

import com.emre.vetproject.core.exception.NotFoundException;
import com.emre.vetproject.core.result.Result;
import com.emre.vetproject.core.result.ResultData;
import com.emre.vetproject.core.utilities.Message;
import com.emre.vetproject.core.utilities.ResultGen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultGen.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {


        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ResultData<List<String>> resultData = new ResultData<>(false,
                Message.VALIDATION_ERROR,
                "400", validationErrorList);
        return new ResponseEntity<>(ResultGen.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleValidationErrors(Exception e) {
        return new ResponseEntity<>(ResultGen.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
