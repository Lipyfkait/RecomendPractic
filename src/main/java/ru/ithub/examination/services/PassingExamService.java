package ru.ithub.examination.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ithub.examination.payload.dto.ExamDto;
import ru.ithub.examination.payload.request.ExamPassingRequest;
import ru.ithub.examination.payload.response.ExamPassingResponse;
import ru.ithub.examination.payload.response.ExamPreviewResponse;

import java.security.Principal;

public interface PassingExamService {
    Page<ExamPreviewResponse> getAllExams(Pageable pageable);
    Page<ExamPassingResponse> getAllPasses(Pageable pageable);
    ExamDto getById(Long id);
    ExamPassingResponse startPassing(Long examId);
    ExamPassingResponse endPassing(Long examPassingId, ExamPassingRequest request);
}
