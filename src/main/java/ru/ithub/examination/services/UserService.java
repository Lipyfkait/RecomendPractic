package ru.ithub.examination.services;

import ru.ithub.examination.domain.entity.User;
import ru.ithub.examination.payload.dto.UserDto;

import java.util.List;

public interface UserService {
    User getCurrent();
    UserDto getById(Long id);
    List<UserDto> getAll();
}
