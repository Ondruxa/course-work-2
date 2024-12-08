package com.homework.exam_questions.service.impl;

import com.homework.exam_questions.exception.NotEnoughQuestionsException;
import com.homework.exam_questions.model.Question;
import com.homework.exam_questions.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void shouldCorrectlyGetRandomQuestions() {
        //given
        int amount = 3;

        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            questions.add(new Question("question" + i, "answer" + i));
        }

        Mockito.when(questionService.getAll()).thenReturn(questions);

        Mockito.when(questionService.getRandomQuestion()).thenReturn(
                questions.get(0), questions.get(0),
                questions.get(1), questions.get(1),
                questions.get(1), questions.get(2)
        );

        //when
        Collection<Question> randomQuestions = examinerService.getQuestions(amount);

        //then
        assertEquals(randomQuestions.size(), amount);
        assertTrue(randomQuestions.containsAll(questions));
        Mockito.verify(questionService, times(6)).getRandomQuestion();
    }

    @Test
    void shouldThrowNotEnoughQuestionsExceptionWhenThereIsNotEnoughQuestions() {
        //give
        int amount = 3;
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount / 2; i++) {
            questions.add(new Question("question" + i, "answer" + i));
        }

        Mockito.when(questionService.getAll()).thenReturn(questions);

        //when
        //then
        assertThrows(
                NotEnoughQuestionsException.class,
                () ->
                    examinerService.getQuestions(amount)
        );
    }
}