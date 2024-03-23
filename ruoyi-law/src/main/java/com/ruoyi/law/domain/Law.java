package com.ruoyi.law.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 法律知识图谱对象 law
 * 
 * @author pwc
 * @date 2024-01-31
 */
public class Law extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 法条序号 */
    private Long id;

    /** 从行为动机上分类 */
    @Excel(name = "从行为动机上分类")
    private String classification;

    /** 优先级第一的关键词 */
    @Excel(name = "优先级第一的关键词")
    private String key1;

    /** 优先级第二的关键词 */
    @Excel(name = "优先级第二的关键词")
    private String key2;

    /** 优先级第三的关键词 */
    @Excel(name = "优先级第三的关键词")
    private String key3;

    /** 优先级第四的关键词 */
    @Excel(name = "优先级第四的关键词")
    private String key4;

    /** 优先级第五的关键词 */
    @Excel(name = "优先级第五的关键词")
    private String key5;

    /** 法条名称 */
    @Excel(name = "法条名称")
    private String clause;

    /** 法律内容 */
    @Excel(name = "法律内容")
    private String content;

    /** 建议 */
    @Excel(name = "建议")
    private String advice;

    /** 0未删除，1已删除 */
    @Excel(name = "0未删除，1已删除")
    private Long isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setClassification(String classification) 
    {
        this.classification = classification;
    }

    public String getClassification() 
    {
        return classification;
    }
    public void setKey1(String key1) 
    {
        this.key1 = key1;
    }

    public String getKey1() 
    {
        return key1;
    }
    public void setKey2(String key2) 
    {
        this.key2 = key2;
    }

    public String getKey2() 
    {
        return key2;
    }
    public void setKey3(String key3) 
    {
        this.key3 = key3;
    }

    public String getKey3() 
    {
        return key3;
    }
    public void setKey4(String key4) 
    {
        this.key4 = key4;
    }

    public String getKey4() 
    {
        return key4;
    }
    public void setKey5(String key5) 
    {
        this.key5 = key5;
    }

    public String getKey5() 
    {
        return key5;
    }
    public void setClause(String clause) 
    {
        this.clause = clause;
    }

    public String getClause() 
    {
        return clause;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setAdvice(String advice) 
    {
        this.advice = advice;
    }

    public String getAdvice() 
    {
        return advice;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("classification", getClassification())
            .append("key1", getKey1())
            .append("key2", getKey2())
            .append("key3", getKey3())
            .append("key4", getKey4())
            .append("key5", getKey5())
            .append("clause", getClause())
            .append("content", getContent())
            .append("advice", getAdvice())
            .append("isDeleted", getIsDeleted())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
