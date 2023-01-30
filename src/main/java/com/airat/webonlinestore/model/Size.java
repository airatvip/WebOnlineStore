package com.airat.webonlinestore.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum Size {
    XS(23),
    S(25),
    M(27),
    L(29),
    XL(31);

    private final int number;

    @JsonValue
    public int getNumber() {
        return number;
    }
}
