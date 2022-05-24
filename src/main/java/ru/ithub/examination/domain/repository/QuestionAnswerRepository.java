package ru.ithub.examination.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ithub.examination.domain.entity.QuestionAnswer;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
}
