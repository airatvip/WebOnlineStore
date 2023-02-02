package com.airat.webonlinestore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    public static Size fromInteger(String number) {
        int number1 = Integer.parseInt(number);
        for (Size s : Size.values()) {
            if (s.number == number1) {
                return s;
            }
        }
        return null;
    }
}
