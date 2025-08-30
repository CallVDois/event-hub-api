package com.callv2.event.hub.infrastructure.converter;

import org.springframework.stereotype.Component;

import com.callv2.event.hub.infrastructure.configuration.mapper.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public final class JacksonCaster implements Caster {

    private static final ObjectMapper mapper = Mapper.mapper();

    private JacksonCaster() {
    }

    @Override
    public <T> T cast(Object value, Class<T> targetType) {
        return mapper.convertValue(value, targetType);
    }

}
