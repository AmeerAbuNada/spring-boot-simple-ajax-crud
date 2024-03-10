package com.ameerabunada.mycrud.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDateConverter implements Converter<String, Date> {

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String source) {
        try {
            return formatter.parse(source);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format. Please use yyyy-MM-dd");
        }
    }
}