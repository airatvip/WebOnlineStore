package com.airat.webonlinestore.service;

import com.airat.webonlinestore.model.Size;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;


public class SizeToEnumConverter implements Converter<String, Size> {
    @Override
    public Size convert(String size) {
        return Size.fromInteger(size);
    }
}
