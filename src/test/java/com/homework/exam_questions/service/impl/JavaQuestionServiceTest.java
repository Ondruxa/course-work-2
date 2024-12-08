package com.homework.exam_questions.service.impl;

import com.homework.exam_questions.model.Question;
import com.homework.exam_questions.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private QuestionService questionService;

    @BeforeEach
    public void clear() {
        questionService = new JavaQuestionService();
    }

    @Test
    void shouldCorrectlyAddQuestion() {
        //given
        Question expectedQuestion = new Question("question", "answer");

        //when
        Question actualQuestion = questionService.add(expectedQuestion);

        //then
        assertEquals(expectedQuestion, actualQuestion);

    }

    @Test
    void remove() {
        //given
        Question expectedQuestion = new Question("question", "answer");
        questionService.add(expectedQuestion);

        //when
        Question actualQuestion = questionService.remove(expectedQuestion);

        //then
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void getAll() {
        //given
        Question question1 = new Question("question", "answer");
        Question question2 = new Question("question", "answer");

        questionService.add(question1);
        questionService.add(question2);

        Set<Question> expectedQuestions = new HashSet<>() {{
            add(question1);
            add(question2);
        }};

        //when
        Collection<Question> actualQuestion = questionService.getAll();

        //then
        assertEquals(expectedQuestions, actualQuestion);
    }

    @Test
    void getRandomQuestion() {
        //given
        Question question1 = new Question("question", "answer");
        Question question2 = new Question("question", "answer");

        questionService.add(question1);
        questionService.add(question2);

        Set<Question> questions = new HashSet<>() {{
            add(question1);
            add(question2);
        }};

        //when
        Question randomQuestion = questionService.getRandomQuestion();

        //then
        assertTrue(questions.contains(randomQuestion));
    }
}