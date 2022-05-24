package ru.ithub.examination.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamPreviewResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    private long questionCount;
}
