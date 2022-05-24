package ru.ithub.examination.core.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> clazz, Long id) {
        super(clazz.getSimpleName() + " не найдет с идентификатором: " + id);
    }

    public NotFoundException(Class<?> clazz, String data) {
        super(clazz.getSimpleName() + " не найден с данными: " + data);
    }
}
