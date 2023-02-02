package com.airat.webonlinestore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Operation {

    private final TypeOperation typeOperation;
    private final LocalDateTime dateTime;
    private final Socks socks;


}
