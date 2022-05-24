package ru.ithub.examination.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ithub.examination.domain.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
