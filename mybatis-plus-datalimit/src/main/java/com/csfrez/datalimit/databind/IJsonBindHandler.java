package com.csfrez.datalimit.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public interface IJsonBindHandler {
    void handle(Object var1, JsonGenerator var2, SerializerProvider var3) throws IOException;
}
