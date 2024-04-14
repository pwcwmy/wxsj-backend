package com.ruoyi.web.controller.wxsj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ruoyi.law.domain.Question;
import com.ruoyi.law.service.IQuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private IQuestionService questionService;


    @Test
    public void testList() {
        Question question = new Question();
        question.setId("1");
//        question.setQuestion1("A.自然人");
        List<Question> expectedList = new ArrayList<>();
        //expectedList = questionService.selectQuestionList(question);
        Question Q1 = questionService.selectQuestionById("1");
        System.out.println("hello");
        System.out.println(Q1);
    }
}