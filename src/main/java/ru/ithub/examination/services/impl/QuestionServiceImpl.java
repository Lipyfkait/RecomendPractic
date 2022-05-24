package ru.ithub.examination.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ithub.examination.core.exceptions.NotFoundException;
import ru.ithub.examination.domain.entity.Question;
import ru.ithub.examination.domain.mappers.QuestionMapper;
import ru.ithub.examination.domain.repository.QuestionRepository;
import ru.ithub.examination.payload.dto.QuestionDto;
import ru.ithub.examination.services.QuestionService;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository repository;
    private final QuestionMapper mapper;

    @Override
    public Page<QuestionDto> getAll(Pageable pageable) {
        Page<Question> entityPage = repository.findAll(pageable);

        return new PageImpl<>(
                new ArrayList<>(mapper.toDto(entityPage.toSet())),
                pageable,
                entityPage.getTotalElements()
        );
    }

    @Override
    public QuestionDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new NotFoundException(Question.class, id)));
    }
}
