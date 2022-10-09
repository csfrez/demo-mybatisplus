package com.csfrez.datalimit.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonBindSerializer extends JsonSerializer<Object> {
    private static IJsonBindHandler JSON_BIND_HANDLER;

    public JsonBindSerializer() {
    }

    public static void setJsonBindHandler(IJsonBindHandler jsonBindHandler) {
        JSON_BIND_HANDLER = jsonBindHandler;
    }

    public void serialize(Object obj, JsonGenerator gen, SerializerProvider provider) throws IOException {
        JSON_BIND_HANDLER.handle(obj, gen, provider);
    }
}