package ru.ithub.examination.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamPassingRequest implements Serializable {
    private Map<Long, Set<Long>> choiceAnswers;
    private Map<Long, String> customAnswers;
}
