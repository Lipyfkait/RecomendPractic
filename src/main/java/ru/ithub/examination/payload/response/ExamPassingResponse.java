package ru.ithub.examination.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ithub.examination.domain.entity.ExamPassing;
import ru.ithub.examination.payload.dto.UserDto;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamPassingResponse implements Serializable {
    private Long id;
    private UserDto user;
    private ExamPreviewResponse exam;
    private LocalDateTime createdAt;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double resultPercentage;
    private ExamPassing.Status status;
}
