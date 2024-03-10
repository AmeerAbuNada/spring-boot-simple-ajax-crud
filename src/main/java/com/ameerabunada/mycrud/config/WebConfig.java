package com.ameerabunada.mycrud.config;

import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ameerabunada.mycrud.converters.StringToDateConverter;

@Component
public class WebConfig implements WebMvcConfigurer {

    private final StringToDateConverter stringToDateConverter;

    public WebConfig(StringToDateConverter stringToDateConverter) {
        this.stringToDateConverter = stringToDateConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToDateConverter);
    }
}
