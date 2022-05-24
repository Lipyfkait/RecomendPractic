package ru.ithub.examination.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ithub.examination.core.exceptions.NotFoundException;
import ru.ithub.examination.domain.entity.Exam;
import ru.ithub.examination.domain.entity.Question;
import ru.ithub.examination.domain.entity.QuestionAnswer;
import ru.ithub.examination.domain.repository.ExamRepository;
import ru.ithub.examination.domain.repository.QuestionAnswerRepository;
import ru.ithub.examination.payload.dto.QuestionAnswerDto;
import ru.ithub.examination.payload.dto.QuestionDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionMapper {
    private final ExamRepository examRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionAnswerMapper questionAnswerMapper;

    public QuestionDto toDto(Question entity) {
        return new QuestionDto(
                entity.getId(),
                entity.getExam().getId(),
                entity.getName(),
                entity.getPosition(),
                entity.getType(),
                entity.getAnswers()
                        .stream()
                        .map(QuestionAnswer::getId)
                        .collect(Collectors.toSet()),
                questionAnswerMapper.toDto(entity.getAnswers())
        );
    }

    public Set<QuestionDto> toDto(Collection<Question> entities) {
        if (entities == null || entities.isEmpty()) {
            return new HashSet<>();
        }

        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public Question toEntity(QuestionDto dto) {
        Question entity = new Question();
        entity.setExam(examRepository.findById(dto.getExamId()).orElseThrow(() -> new NotFoundException(Exam.class, dto.getExamId())));
        entity.setName(dto.getName());
        entity.setPosition(dto.getPosition());
        entity.setType(dto.getType());

        Set<QuestionAnswer> answers = new HashSet<>();

        if (dto.getAnswersIds() != null && !dto.getAnswersIds().isEmpty()) {
            answers.addAll(questionAnswerRepository.findAllById(dto.getAnswersIds()));
        }

        for (QuestionAnswerDto creation : dto.getAnswers()) {
            QuestionAnswer creationEntity = questionAnswerMapper.toEntity(creation);

            boolean exist = false;

            for (QuestionAnswer existing : answers) {
                if (existing.contentEquals(creationEntity)) {
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                answers.add(creationEntity);
            }
        }



        entity.setAnswers(answers);

        return entity;
    }

    public Set<Question> toEntity(Collection<QuestionDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return new HashSet<>();
        }

        return dtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
