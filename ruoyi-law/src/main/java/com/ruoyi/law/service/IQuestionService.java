package com.ruoyi.law.service;

import java.util.List;
import com.ruoyi.law.domain.Question;

/**
 * 问卷答案组合Service接口
 * 
 * @author pwc
 * @date 2024-04-13
 */
public interface IQuestionService 
{
    /**
     * 查询问卷答案组合
     * 
     * @param id 问卷答案组合主键
     * @return 问卷答案组合
     */
    public Question selectQuestionById(String id);

    /**
     * 查询问卷答案组合列表
     * 
     * @param question 问卷答案组合
     * @return 问卷答案组合集合
     */
    public List<Question> selectQuestionList(Question question);

    /**
     * 新增问卷答案组合
     * 
     * @param question 问卷答案组合
     * @return 结果
     */
    public int insertQuestion(Question question);

    /**
     * 修改问卷答案组合
     * 
     * @param question 问卷答案组合
     * @return 结果
     */
    public int updateQuestion(Question question);

    /**
     * 批量删除问卷答案组合
     * 
     * @param ids 需要删除的问卷答案组合主键集合
     * @return 结果
     */
    public int deleteQuestionByIds(String[] ids);

    /**
     * 删除问卷答案组合信息
     * 
     * @param id 问卷答案组合主键
     * @return 结果
     */
    public int deleteQuestionById(String id);
}
