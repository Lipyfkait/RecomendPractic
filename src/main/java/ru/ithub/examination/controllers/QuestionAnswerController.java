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
import ru.ithub.examination.payload.dto.QuestionAnswerDto;
import ru.ithub.examination.services.QuestionAnswerService;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class QuestionAnswerController {
    private final QuestionAnswerService service;

    @GetMapping(Router.Exam.Question.Answer.ROOT)
    public ApiResponse<Page<QuestionAnswerDto>> getAll(Pageable pageable) {
        return ApiResponse.success(service.getAll(pageable));
    }

    @GetMapping(Router.Exam.Question.Answer.Id.ROOT)
    public ApiResponse<QuestionAnswerDto> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id));
    }
}
