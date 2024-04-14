package com.ruoyi.law.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问卷答案组合对象 question
 * 
 * @author pwc
 * @date 2024-04-13
 */
public class Question extends BaseEntity
{
    public Question() {
    }

    public Question(String id, String question1, String question2, String question3, String question4, String question5, String question6, String legalLiability) {
        this.id = id;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.legalLiability = legalLiability;
    }

    private static final long serialVersionUID = 1L;

    /** 答案组合id */
    private String id;

    /** 问题1答案 */
    @Excel(name = "问题1答案")
    private String question1;

    /** 问题2答案 */
    @Excel(name = "问题2答案")
    private String question2;

    /** 问题3答案 */
    @Excel(name = "问题3答案")
    private String question3;

    /** 问题4答案 */
    @Excel(name = "问题4答案")
    private String question4;

    /** 问题5答案 */
    @Excel(name = "问题5答案")
    private String question5;

    /** 问题6答案 */
    @Excel(name = "问题6答案")
    private String question6;

    /** 法律责任 */
    @Excel(name = "法律责任")
    private String legalLiability;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setQuestion1(String question1) 
    {
        this.question1 = question1;
    }

    public String getQuestion1() 
    {
        return question1;
    }
    public void setQuestion2(String question2) 
    {
        this.question2 = question2;
    }

    public String getQuestion2() 
    {
        return question2;
    }
    public void setQuestion3(String question3) 
    {
        this.question3 = question3;
    }

    public String getQuestion3() 
    {
        return question3;
    }
    public void setQuestion4(String question4) 
    {
        this.question4 = question4;
    }

    public String getQuestion4() 
    {
        return question4;
    }
    public void setQuestion5(String question5) 
    {
        this.question5 = question5;
    }

    public String getQuestion5() 
    {
        return question5;
    }
    public void setQuestion6(String question6) 
    {
        this.question6 = question6;
    }

    public String getQuestion6() 
    {
        return question6;
    }
    public void setLegalLiability(String legalLiability) 
    {
        this.legalLiability = legalLiability;
    }

    public String getLegalLiability() 
    {
        return legalLiability;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("question1", getQuestion1())
            .append("question2", getQuestion2())
            .append("question3", getQuestion3())
            .append("question4", getQuestion4())
            .append("question5", getQuestion5())
            .append("question6", getQuestion6())
            .append("legalLiability", getLegalLiability())
            .toString();
    }
}
