package ru.ithub.examination.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ithub.examination.core.router.Router;
import ru.ithub.examination.payload.ApiResponse;
import ru.ithub.examination.payload.dto.UserDto;
import ru.ithub.examination.services.UserService;

import java.util.List;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(Router.User.ROOT)
    public ApiResponse<List<UserDto>> getAll() {
        return ApiResponse.success(userService.getAll());
    }

    @GetMapping(Router.User.Id.ROOT)
    public ApiResponse<UserDto> getById(@PathVariable Long id) {
        return ApiResponse.success(userService.getById(id));
    }
}
