package ru.voroby.storebackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> exceptionHandle(MethodArgumentNotValidException ex) {
    Map<String, String> map = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String defaultMessage = error.getDefaultMessage();
      map.put(fieldName, defaultMessage);
    });

    return map;
  }

}
