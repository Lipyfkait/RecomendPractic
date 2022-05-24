package ru.ithub.examination.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ithub.examination.payload.dto.QuestionDto;

public interface QuestionService {
    Page<QuestionDto> getAll(Pageable pageable);
    QuestionDto getById(Long id);
}
