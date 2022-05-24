package ru.ithub.examination.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ithub.examination.payload.dto.ExamDto;

import java.util.Collection;

public interface ExamService {
    Page<ExamDto> getAll(Pageable pageable);
    ExamDto getById(Long id);
    ExamDto create(ExamDto dto);
    ExamDto update(Long id, ExamDto dto);
    void delete(Collection<Long> ids);
}
