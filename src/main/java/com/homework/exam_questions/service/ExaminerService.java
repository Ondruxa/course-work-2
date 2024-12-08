package com.homework.exam_questions.service;

import com.homework.exam_questions.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions (int amount);
}
