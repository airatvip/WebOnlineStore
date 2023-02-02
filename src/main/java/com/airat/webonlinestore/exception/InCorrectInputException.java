package com.airat.webonlinestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InCorrectInputException extends RuntimeException {
   public InCorrectInputException (String message) {
       super(message);
   }
}
