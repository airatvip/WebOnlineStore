package com.airat.webonlinestore.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypeOperation {
    ADD("Приемка"),
    REMOVE("Списание"),
    RELEASE("Выдача");

    private final String translation;

    @JsonValue
    public String getTranslation() {
        return translation;
    }
}
