package com.ruoyi.law.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.law.mapper.QuestionMapper;
import com.ruoyi.law.domain.Question;
import com.ruoyi.law.service.IQuestionService;

/**
 * 问卷答案组合Service业务层处理
 * 
 * @author pwc
 * @date 2024-04-13
 */
@Service
public class QuestionServiceImpl implements IQuestionService 
{
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 查询问卷答案组合
     * 
     * @param id 问卷答案组合主键
     * @return 问卷答案组合
     */
    @Override
    public Question selectQuestionById(String id)
    {
        return questionMapper.selectQuestionById(id);
    }

    /**
     * 查询问卷答案组合列表
     * 
     * @param question 问卷答案组合
     * @return 问卷答案组合
     */
    @Override
    public List<Question> selectQuestionList(Question question)
    {
        return questionMapper.selectQuestionList(question);
    }

    /**
     * 新增问卷答案组合
     * 
     * @param question 问卷答案组合
     * @return 结果
     */
    @Override
    public int insertQuestion(Question question)
    {
        return questionMapper.insertQuestion(question);
    }

    /**
     * 修改问卷答案组合
     * 
     * @param question 问卷答案组合
     * @return 结果
     */
    @Override
    public int updateQuestion(Question question)
    {
        return questionMapper.updateQuestion(question);
    }

    /**
     * 批量删除问卷答案组合
     * 
     * @param ids 需要删除的问卷答案组合主键
     * @return 结果
     */
    @Override
    public int deleteQuestionByIds(String[] ids)
    {
        return questionMapper.deleteQuestionByIds(ids);
    }

    /**
     * 删除问卷答案组合信息
     * 
     * @param id 问卷答案组合主键
     * @return 结果
     */
    @Override
    public int deleteQuestionById(String id)
    {
        return questionMapper.deleteQuestionById(id);
    }
}
