package com.airat.webonlinestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuantityException extends RuntimeException {
    public QuantityException(String message) {
        super(message);
    }

}
