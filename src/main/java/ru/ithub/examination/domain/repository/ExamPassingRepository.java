package ru.ithub.examination.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ithub.examination.domain.entity.ExamPassing;

public interface ExamPassingRepository extends JpaRepository<ExamPassing, Long> {
    Page<ExamPassing> findAllByUser_Id(Long userId, Pageable pageable);
}
