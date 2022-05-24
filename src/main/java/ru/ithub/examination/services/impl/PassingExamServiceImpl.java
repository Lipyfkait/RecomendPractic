package ru.ithub.examination.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ithub.examination.core.exceptions.AccessDeniedException;
import ru.ithub.examination.core.exceptions.NotFoundException;
import ru.ithub.examination.domain.entity.Exam;
import ru.ithub.examination.domain.entity.ExamPassing;
import ru.ithub.examination.domain.entity.Question;
import ru.ithub.examination.domain.entity.QuestionAnswer;
import ru.ithub.examination.domain.entity.User;
import ru.ithub.examination.domain.mappers.ExamMapper;
import ru.ithub.examination.domain.mappers.ExamPassingMapper;
import ru.ithub.examination.domain.repository.ExamPassingRepository;
import ru.ithub.examination.domain.repository.ExamRepository;
import ru.ithub.examination.payload.dto.ExamDto;
import ru.ithub.examination.payload.dto.QuestionAnswerDto;
import ru.ithub.examination.payload.dto.QuestionDto;
import ru.ithub.examination.payload.request.ExamPassingRequest;
import ru.ithub.examination.payload.response.ExamPassingResponse;
import ru.ithub.examination.payload.response.ExamPreviewResponse;
import ru.ithub.examination.services.PassingExamService;
import ru.ithub.examination.services.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
class PassingExamServiceImpl implements PassingExamService {
    private final ExamRepository examRepository;
    private final ExamPassingRepository examPassingRepository;
    private final ExamMapper examMapper;
    private final UserService userService;
    private final ExamPassingMapper examPassingMapper;

    @Override
    public Page<ExamPreviewResponse> getAllExams(Pageable pageable) {
        Page<Exam> entityPage = examRepository.findAll(pageable);

        return new PageImpl<>(
                new ArrayList<>(examMapper.toPreviewResponse(entityPage.toSet())),
                pageable,
                entityPage.getTotalElements()
        );
    }

    @Override
    public Page<ExamPassingResponse> getAllPasses(Pageable pageable) {
        User user = userService.getCurrent();

        if (user == null) throw new AccessDeniedException();

        Page<ExamPassing> entityPage = examPassingRepository.findAllByUser_Id(user.getId(), pageable);

        return new PageImpl<>(
                new ArrayList<>(examPassingMapper.toResponse(entityPage.toSet())),
                pageable,
                entityPage.getTotalElements()
        );
    }

    @Override
    public ExamDto getById(Long id) {
        ExamDto dto = examMapper.toDto(examRepository.findById(id).orElseThrow(() -> new NotFoundException(Exam.class, id)));

        for (QuestionDto question : dto.getQuestions()) {
            if (question.getType() == Question.Type.CUSTOM) {
                question.getAnswers().clear();
                question.getAnswersIds().clear();
            } else {
                for (QuestionAnswerDto answer : question.getAnswers()) {
                    answer.setIsCorrect(null);
                }
            }
        }

        return dto;
    }

    @Override
    public ExamPassingResponse startPassing(Long examId) {
        ExamPassing entity = new ExamPassing();
        entity.setUser(userService.getCurrent());
        entity.setExam(examRepository.findById(examId).orElseThrow(() -> new NotFoundException(Exam.class, examId)));
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStartTime(LocalDateTime.now());

        return examPassingMapper.toResponse(
                examPassingRepository.save(
                        entity
                )
        );
    }

    @Override
    public ExamPassingResponse endPassing(Long examPassingId, ExamPassingRequest request) {
        ExamPassing entity = examPassingRepository.findById(examPassingId).orElseThrow(() -> new NotFoundException(ExamPassing.class, examPassingId));
        entity.setEndTime(LocalDateTime.now());

        long questionSize = entity.getExam().getQuestions().size();
        long correctAnswersSize = 0L;

        for (Question question : entity.getExam().getQuestions()) {
            final Set<QuestionAnswer> answers = question.getAnswers();
            if (question.getType() == Question.Type.CHOICE && request.getChoiceAnswers().containsKey(question.getId())) {

                final Set<Long> correctIds = answers.stream().filter(QuestionAnswer::getIsCorrect).map(QuestionAnswer::getId).collect(Collectors.toSet());

                if (correctIds.equals(request.getChoiceAnswers().get(question.getId()))) {
                    correctAnswersSize += 1;
                }

            }

            if (question.getType() == Question.Type.CUSTOM && request.getCustomAnswers().containsKey(question.getId())) {

                String userAnswer = request.getCustomAnswers().get(question.getId());


                for (QuestionAnswer answer : answers) {
                    if (toLowerCaseWithTrim(answer.getValue()).equals(toLowerCaseWithTrim(userAnswer))) {
                        correctAnswersSize += 1;
                    }
                }
            }
        }

        entity.setResultPercentage(Double.parseDouble(String.valueOf(correctAnswersSize / questionSize)));
        entity.setStatus(ExamPassing.Status.COMPLETE);

        return examPassingMapper.toResponse(
                examPassingRepository.save(entity)
        );
    }

    private String toLowerCaseWithTrim(String original) {
        return original.trim().toLowerCase();
    }
}
