package com.ruoyi.law.mapper;

import java.util.List;
import com.ruoyi.law.domain.Question;
import org.springframework.stereotype.Repository;

/**
 * 问卷答案组合Mapper接口
 * 
 * @author pwc
 * @date 2024-04-13
 */
@Repository
public interface QuestionMapper 
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
     * 删除问卷答案组合
     * 
     * @param id 问卷答案组合主键
     * @return 结果
     */
    public int deleteQuestionById(String id);

    /**
     * 批量删除问卷答案组合
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionByIds(String[] ids);
}
