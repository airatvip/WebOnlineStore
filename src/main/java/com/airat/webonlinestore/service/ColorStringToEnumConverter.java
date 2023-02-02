package com.airat.webonlinestore.service;

import com.airat.webonlinestore.model.Color;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;


public class ColorStringToEnumConverter implements Converter<String, Color> {

    @Override
    public Color convert(String translation) {
       return Color.fromString(translation);
    }

}

