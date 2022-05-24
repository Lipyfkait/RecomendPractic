package ru.ithub.examination.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ithub.examination.domain.entity.Question;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto implements Serializable {
    private Long id;
    private Long examId;
    private String name;
    private int position;
    private Question.Type type;
    private Set<Long> answersIds;
    private Set<QuestionAnswerDto> answers;
}
