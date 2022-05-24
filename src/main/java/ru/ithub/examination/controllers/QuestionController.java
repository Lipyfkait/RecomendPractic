package ru.ithub.examination.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ithub.examination.core.router.Router;
import ru.ithub.examination.payload.ApiResponse;
import ru.ithub.examination.payload.dto.QuestionDto;
import ru.ithub.examination.services.QuestionService;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService service;

    @GetMapping(Router.Exam.Question.ROOT)
    public ApiResponse<Page<QuestionDto>> getAll(Pageable pageable) {
        return ApiResponse.success(service.getAll(pageable));
    }

    @GetMapping(Router.Exam.Question.Id.ROOT)
    public ApiResponse<QuestionDto> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id));
    }
}