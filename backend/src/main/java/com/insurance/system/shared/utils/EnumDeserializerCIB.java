package com.insurance.system.shared.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class EnumDeserializerCIB<T extends Enum<T>> extends StdDeserializer<T> {

    private final Class<T> enumClass;

    public EnumDeserializerCIB(Class<T> enumClass) {
        super(enumClass);
        this.enumClass = enumClass;
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.readValueAs(String.class);
        value = value.replaceAll(" ", "_");
        return Enum.valueOf(enumClass, value);
    }
}
