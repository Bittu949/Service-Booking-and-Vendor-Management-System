package com.sqts.sbvms.Json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sqts.sbvms.Exception.InvalidDurationException;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class DurationDeserializer extends JsonDeserializer<Duration> {

    @Override
    public Duration deserialize(JsonParser parser,
                                DeserializationContext context)
            throws IOException {

        String value = parser.getText().trim();

        try {

            LocalTime time = LocalTime.parse(value);

            return Duration.ofHours(time.getHour())
                    .plusMinutes(time.getMinute());

        } catch (DateTimeParseException ex) {

            throw new InvalidDurationException(
                    "Invalid duration. Please use HH:mm format (e.g. 02:30)."
            );
        }
    }
}