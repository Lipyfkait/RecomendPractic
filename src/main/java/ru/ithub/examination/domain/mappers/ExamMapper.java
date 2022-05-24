package ru.ithub.examination.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ithub.examination.domain.entity.Exam;
import ru.ithub.examination.domain.entity.Question;
import ru.ithub.examination.domain.repository.QuestionRepository;
import ru.ithub.examination.payload.dto.ExamDto;
import ru.ithub.examination.payload.dto.QuestionDto;
import ru.ithub.examination.payload.response.ExamPreviewResponse;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamMapper {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public ExamPreviewResponse toPreviewResponse(Exam entity) {
        return new ExamPreviewResponse(entity.getId(), entity.getName(), entity.getDescription(), entity.getQuestions().size());
    }

    public Set<ExamPreviewResponse> toPreviewResponse(Collection<Exam> entities) {
        if (entities == null || entities.isEmpty()) {
            return new HashSet<>();
        }

        return entities.stream().map(this::toPreviewResponse).collect(Collectors.toSet());
    }

    public ExamDto toDto(Exam entity) {
        return new ExamDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getQuestions()
                        .stream()
                        .map(Question::getId)
                        .collect(Collectors.toSet()),
                questionMapper.toDto(entity.getQuestions())
        );
    }

    public Set<ExamDto> toDto(Collection<Exam> entities) {
        if (entities == null || entities.isEmpty()) {
            return new HashSet<>();
        }

        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public Exam toEntity(ExamDto dto) {
        Exam entity = new Exam();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        Set<Question> questions = new HashSet<>();

        if (dto.getQuestionsIds() != null && !dto.getQuestionsIds().isEmpty()) {
            questions.addAll(questionRepository.findAllById(dto.getQuestionsIds()));
        }

        for (QuestionDto creation : dto.getQuestions()) {
            Question creationEntity = questionMapper.toEntity(creation);

            boolean exist = false;

            for (Question existing : questions) {
                if (existing.contentEquals(creationEntity)) {
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                questions.add(creationEntity);
            }
        }



        entity.setQuestions(questions);

        return entity;
    }

    public Exam cleanUpdate(ExamDto dto, Exam entity) {
        Exam tps = toEntity(dto);
        tps.setId(entity.getId());

        return tps;
    }
    public Set<Exam> toEntity(Collection<ExamDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return new HashSet<>();
        }

        return dtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }


}
