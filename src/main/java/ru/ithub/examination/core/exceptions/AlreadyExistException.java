package ru.ithub.examination.core.exceptions;

import java.util.Map;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(Class<?> clazz, Map<String, Object> data) {
        super(clazz.getSimpleName() + " уже существует с такими данными: " + data);
    }
}
