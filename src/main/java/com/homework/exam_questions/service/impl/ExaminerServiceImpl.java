package com.homework.exam_questions.service.impl;

import com.homework.exam_questions.exception.NotEnoughQuestionsException;
import com.homework.exam_questions.model.Question;
import com.homework.exam_questions.service.ExaminerService;
import com.homework.exam_questions.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int questionTotalSize = questionService.getAll().size();
        if (amount > questionTotalSize) {
            throw new NotEnoughQuestionsException("Запрошено " + amount + "вопросов, доступно " + questionTotalSize);
        }

        Set<Question> randomQuestions = new HashSet<>();

        while (randomQuestions.size() < amount) {
            randomQuestions.add(questionService.getRandomQuestion());
        }

        return Collections.unmodifiableSet(randomQuestions);
    }
}
