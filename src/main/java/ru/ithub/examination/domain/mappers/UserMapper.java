package ru.ithub.examination.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ithub.examination.domain.entity.User;
import ru.ithub.examination.payload.dto.UserDto;
import ru.ithub.examination.services.RoleService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleService roleService;
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getRole().getName());
    }

    public List<UserDto> toDto(Collection<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public User toEntity(UserDto dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setMiddleName(dto.getMiddleName());
        user.setRole(roleService.get(dto.getRole()));

        return user;
    }

    public List<User> toEntity(Collection<UserDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return new ArrayList<>();
        }

        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
