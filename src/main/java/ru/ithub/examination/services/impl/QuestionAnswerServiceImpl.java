package ru.ithub.examination.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ithub.examination.core.exceptions.NotFoundException;
import ru.ithub.examination.domain.entity.QuestionAnswer;
import ru.ithub.examination.domain.mappers.QuestionAnswerMapper;
import ru.ithub.examination.domain.repository.QuestionAnswerRepository;
import ru.ithub.examination.payload.dto.QuestionAnswerDto;
import ru.ithub.examination.services.QuestionAnswerService;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
class QuestionAnswerServiceImpl implements QuestionAnswerService {
    private final QuestionAnswerRepository repository;
    private final QuestionAnswerMapper mapper;

    @Override
    public Page<QuestionAnswerDto> getAll(Pageable pageable) {
        Page<QuestionAnswer> entityPage = repository.findAll(pageable);

        return new PageImpl<>(
                new ArrayList<>(mapper.toDto(entityPage.toSet())),
                pageable,
                entityPage.getTotalElements()
        );
    }

    @Override
    public QuestionAnswerDto getById(Long id) {
        return mapper.toDto(
                repository.findById(id).orElseThrow(() -> new NotFoundException(QuestionAnswer.class, id))
        );
    }
}
