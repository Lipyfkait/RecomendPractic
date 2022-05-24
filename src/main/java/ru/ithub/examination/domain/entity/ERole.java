package ru.ithub.examination.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.ithub.examination.core.exceptions.NotFoundException;

@Getter
@RequiredArgsConstructor
public enum ERole {
    ROLE_USER(1L),
    ROLE_ADMIN(2L);

    private final Long id;

    public static ERole getByName(String role) {
        try {
            return valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(ERole.class, role);
        }
    }
}
