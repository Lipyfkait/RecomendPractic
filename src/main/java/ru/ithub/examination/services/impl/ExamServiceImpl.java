package ru.ithub.examination.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ithub.examination.core.exceptions.NotFoundException;
import ru.ithub.examination.domain.entity.Exam;
import ru.ithub.examination.domain.mappers.ExamMapper;
import ru.ithub.examination.domain.repository.ExamRepository;
import ru.ithub.examination.payload.dto.ExamDto;
import ru.ithub.examination.services.ExamService;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
class ExamServiceImpl implements ExamService {
    private final ExamRepository repository;
    private final ExamMapper mapper;

    @Override
    public Page<ExamDto> getAll(Pageable pageable) {
        Page<Exam> entityPage = repository.findAll(pageable);

        return new PageImpl<>(
                new ArrayList<>(mapper.toDto(entityPage.toSet())),
                pageable,
                entityPage.getTotalElements()
        );
    }

    @Override
    public ExamDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new NotFoundException(Exam.class, id)));
    }

    @Override
    public ExamDto create(ExamDto dto) {
        return mapper.toDto(
                repository.save(
                        mapper.toEntity(dto)
                )
        );
    }

    @Override
    public ExamDto update(Long id, ExamDto dto) {
        return mapper.toDto(
                repository.save(
                        mapper.cleanUpdate(
                                dto,
                                repository.findById(id).orElseThrow(
                                        () -> new NotFoundException(Exam.class, id)
                                )
                        )
                )
        );
    }

    @Override
    public void delete(Collection<Long> ids) {
        repository.deleteAllById(ids);
    }
}
