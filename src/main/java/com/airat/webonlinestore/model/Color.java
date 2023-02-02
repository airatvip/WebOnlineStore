package com.airat.webonlinestore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum Color {
    BLACK("Черный"),
    WHITE("Белый"),
    YELLOW("Желтый"),
    BLUE("Синий"),
    RED("Красный"),
    MULTICOLORS("Разноцветый");

    private final String translation;

    @JsonValue
    public String getTranslation() {
        return translation;
    }


    public static Color fromString(String text) {
        for (Color b : Color.values()) {
            if (b.translation.equals(text)) {
                return b;
            }
        }
        return null;
    }
}

