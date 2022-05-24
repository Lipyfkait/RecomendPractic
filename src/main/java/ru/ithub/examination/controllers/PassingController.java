package ru.ithub.examination.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ithub.examination.core.router.Router;
import ru.ithub.examination.payload.ApiResponse;
import ru.ithub.examination.payload.dto.ExamDto;
import ru.ithub.examination.payload.request.ExamPassingRequest;
import ru.ithub.examination.payload.response.ExamPassingResponse;
import ru.ithub.examination.payload.response.ExamPreviewResponse;
import ru.ithub.examination.services.PassingExamService;

import java.security.Principal;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class PassingController {
    private final PassingExamService service;

    @GetMapping(Router.Passing.Search.Exam.ROOT)
    public ApiResponse<Page<ExamPreviewResponse>> getAllExams(Pageable pageable) {
        return ApiResponse.success(service.getAllExams(pageable));
    }

    @GetMapping(Router.Passing.Search.Passes.ROOT)
    public ApiResponse<Page<ExamPassingResponse>> getAllPasses(Pageable pageable) {
        return ApiResponse.success(service.getAllPasses(pageable));
    }

    @GetMapping(Router.Passing.Search.Exam.Id.ROOT)
    public ApiResponse<ExamDto> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id));
    }

    @PostMapping(Router.Passing.Start.ROOT)
    public ApiResponse<ExamPassingResponse> startPassing(@PathVariable Long examId) {
        return ApiResponse.success(service.startPassing(examId));
    }

    @PostMapping(Router.Passing.End.ROOT)
    public ApiResponse<ExamPassingResponse> endPassing(@PathVariable Long examPassingId, @RequestBody ExamPassingRequest request) {
        return ApiResponse.success(service.endPassing(examPassingId, request));
    }
}
