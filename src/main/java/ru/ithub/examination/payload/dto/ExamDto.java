package ru.ithub.examination.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Set<Long> questionsIds;
    private Set<QuestionDto> questions;
}
