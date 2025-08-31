package com.callv2.event.hub.infrastructure.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public final class ReflectionUtils {

    private ReflectionUtils() {
        // Utility class
    }

    public static Class<?> getComplexFieldClass(final Class<?> clazz, final String field) {
        final Queue<String> fieldsQueue = new LinkedList<String>(Arrays.asList(field.split("\\.")));
        return recursive(clazz, fieldsQueue);
    }

    public static Class<?> getFieldClass(final Class<?> clazz, final String fieldName) {
        try {
            final Field field = clazz.getDeclaredField(fieldName);
            final Class<?> type = field.getType();

            if (type.isArray())
                return type.getComponentType();

            if (Collection.class.isAssignableFrom(type)) {
                final Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType parameterizedType) {
                    final Type[] actualTypes = parameterizedType.getActualTypeArguments();
                    if (actualTypes.length > 0 && actualTypes[0] instanceof Class<?>) {
                        return (Class<?>) actualTypes[0];
                    }
                }
                return Object.class;
            }

            return type;

        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<?> recursive(Class<?> clazz, Queue<String> fieldsQueue) {
        if (fieldsQueue.isEmpty())
            return clazz;

        return recursive(getFieldClass(clazz, fieldsQueue.poll()), fieldsQueue);
    }

}
