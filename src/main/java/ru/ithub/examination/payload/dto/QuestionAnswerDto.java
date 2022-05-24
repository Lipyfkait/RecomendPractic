package ru.ithub.examination.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerDto implements Serializable {
    private Long id;
    private String value;
    private Boolean isCorrect = false;
}
