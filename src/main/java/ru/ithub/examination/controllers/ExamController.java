package ru.ithub.examination.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ithub.examination.core.router.Router;
import ru.ithub.examination.payload.ApiResponse;
import ru.ithub.examination.payload.dto.ExamDto;
import ru.ithub.examination.services.ExamService;

import java.util.Arrays;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class ExamController {
    private final ExamService service;

    @GetMapping(Router.Exam.ROOT)
    public ApiResponse<Page<ExamDto>> getAll(Pageable pageable) {
        return ApiResponse.success(service.getAll(pageable));
    }

    @GetMapping(Router.Exam.Id.ROOT)
    public ApiResponse<ExamDto> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id));
    }

    @PostMapping(Router.Exam.ROOT)
    public ApiResponse<ExamDto> create(@RequestBody ExamDto dto) {
        return ApiResponse.success(service.create(dto));
    }

    @PutMapping(Router.Exam.Id.ROOT)
    public ApiResponse<ExamDto> update(@PathVariable Long id, @RequestBody ExamDto dto) {
        return ApiResponse.success(service.update(id, dto));
    }

    @DeleteMapping(Router.Exam.ROOT)
    public ApiResponse<Void> delete(@RequestBody Long[] ids) {
        service.delete(Arrays.asList(ids));
        return ApiResponse.success();
    }
}
