package com.sqts.sbvms.Json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Duration;

public class DurationSerializer extends JsonSerializer<Duration> {

    @Override
    public void serialize(Duration duration,
                          JsonGenerator generator,
                          SerializerProvider serializers)
            throws IOException {

        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();

        generator.writeString(
                String.format("%02d:%02d", hours, minutes)
        );
    }
}