package com.sqts.sbvms.Config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sqts.sbvms.Json.DurationDeserializer;
import com.sqts.sbvms.Json.DurationSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class JacksonConfig {

    @Bean
    public Module durationModule() {

        SimpleModule module = new SimpleModule();

        module.addSerializer(Duration.class, new DurationSerializer());
        module.addDeserializer(Duration.class, new DurationDeserializer());

        return module;
    }
}