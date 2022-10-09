package com.csfrez.datalimit.annotation;

import com.csfrez.datalimit.databind.JsonBindSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(
        using = JsonBindSerializer.class
)
public @interface JsonBind {
    String value();
}
