package ru.ithub.examination.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ithub.examination.domain.entity.QuestionAnswer;
import ru.ithub.examination.payload.dto.QuestionAnswerDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionAnswerMapper {
    public QuestionAnswerDto toDto(QuestionAnswer entity) {
        return new QuestionAnswerDto(entity.getId(), entity.getValue(), entity.getIsCorrect());
    }

    public Set<QuestionAnswerDto> toDto(Collection<QuestionAnswer> entities) {
        if (entities == null || entities.isEmpty()) {
            return new HashSet<>();
        }

        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public QuestionAnswer toEntity(QuestionAnswerDto dto) {
        QuestionAnswer entity = new QuestionAnswer();

        entity.setValue(dto.getValue());
        entity.setIsCorrect(dto.getIsCorrect());

        return entity;
    }

    public Set<QuestionAnswer> toEntity(Collection<QuestionAnswerDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return new HashSet<>();
        }

        return dtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
