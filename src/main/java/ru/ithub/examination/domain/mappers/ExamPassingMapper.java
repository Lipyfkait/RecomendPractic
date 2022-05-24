package ru.ithub.examination.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ithub.examination.domain.entity.ExamPassing;
import ru.ithub.examination.payload.response.ExamPassingResponse;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamPassingMapper {
    private final UserMapper userMapper;
    private final ExamMapper examMapper;

    public ExamPassingResponse toResponse(ExamPassing entity) {
        ExamPassingResponse response = new ExamPassingResponse();
        response.setId(entity.getId());
        response.setUser(userMapper.toDto(entity.getUser()));
        response.setExam(examMapper.toPreviewResponse(entity.getExam()));
        response.setCreatedAt(entity.getCreatedAt());
        response.setEndTime(entity.getEndTime());
        response.setResultPercentage(entity.getResultPercentage());
        response.setStatus(entity.getStatus());

        return response;
    }

    public Set<ExamPassingResponse> toResponse(Collection<ExamPassing> entities) {
        if (entities == null || entities.isEmpty()) {
            return  new HashSet<>();
        }

        return entities.stream().map(this::toResponse).collect(Collectors.toSet());
    }
}
