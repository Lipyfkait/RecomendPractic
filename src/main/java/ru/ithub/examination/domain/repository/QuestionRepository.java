package ru.ithub.examination.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ithub.examination.domain.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
