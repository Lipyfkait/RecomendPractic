package ru.ithub.examination.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ithub.examination.core.exceptions.AuthenticationException;
import ru.ithub.examination.core.exceptions.NotFoundException;
import ru.ithub.examination.domain.entity.User;
import ru.ithub.examination.domain.mappers.UserMapper;
import ru.ithub.examination.domain.repository.UserRepository;
import ru.ithub.examination.payload.dto.UserDto;
import ru.ithub.examination.services.UserService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public User getCurrent() {
        return repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new AuthenticationException("Необходима авторизация"));
    }

    public UserDto getById(Long id) {
        return mapper.toDto(
                repository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id))
        );
    }
    public List<UserDto> getAll() {
        return mapper.toDto(
                repository.findAll()
        );
    }
}
