package com.insurance.system.shared.utils;


import com.fasterxml.jackson.core.JsonGenerator;
        import com.fasterxml.jackson.databind.JsonSerializer;
        import com.fasterxml.jackson.databind.SerializerProvider;

        import java.io.IOException;

public class EnumSerializerCIB extends JsonSerializer<Enum<?>> {

    @Override
    public void serialize(Enum<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.toString().replace("_", " ")); // Replace underscores with spaces
    }

}
