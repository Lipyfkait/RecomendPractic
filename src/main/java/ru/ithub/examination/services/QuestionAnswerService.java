package ru.ithub.examination.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ithub.examination.payload.dto.QuestionAnswerDto;

public interface QuestionAnswerService {
    Page<QuestionAnswerDto> getAll(Pageable pageable);
    QuestionAnswerDto getById(Long id);
}
