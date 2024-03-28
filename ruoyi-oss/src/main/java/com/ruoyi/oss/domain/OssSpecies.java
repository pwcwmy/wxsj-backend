package com.ruoyi.oss.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物种数据库信息对象 oss_species
 *
 * @author pwc
 * @date 2024-03-29
 */
public class OssSpecies extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物种id */
    private Long id;

    /** 物种名 */
    @Excel(name = "物种名")
    private String name;

    /** 物种所属的名录id */
    @Excel(name = "物种所属的名录id")
    private String belongListId;

    /** 物种简介 */
    @Excel(name = "物种简介")
    private String speciesInfo;

    /** 危险等级 */
    @Excel(name = "危险等级")
    private String dangerLevel;

    /** 评级依据 */
    @Excel(name = "评级依据")
    private String dangerLevelReason;

    /** 措施 */
    @Excel(name = "措施")
    private String measures;

    /** 法条id */
    @Excel(name = "法条id")
    private String lawId;

    /** 解决方案 */
    @Excel(name = "解决方案")
    private String solution;

    /** 物种图片1 */
    @Excel(name = "物种图片1")
    private String picture1;

    /** 物种图片2 */
    @Excel(name = "物种图片2")
    private String picture2;

    /** 物种图片3 */
    @Excel(name = "物种图片3")
    private String picture3;

    /** 逻辑删除：0未删除，1已删除 */
    @Excel(name = "逻辑删除：0未删除，1已删除")
    private String isDeleted;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setBelongListId(String belongListId)
    {
        this.belongListId = belongListId;
    }

    public String getBelongListId()
    {
        return belongListId;
    }
    public void setSpeciesInfo(String speciesInfo)
    {
        this.speciesInfo = speciesInfo;
    }

    public String getSpeciesInfo()
    {
        return speciesInfo;
    }
    public void setDangerLevel(String dangerLevel)
    {
        this.dangerLevel = dangerLevel;
    }

    public String getDangerLevel()
    {
        return dangerLevel;
    }
    public void setDangerLevelReason(String dangerLevelReason)
    {
        this.dangerLevelReason = dangerLevelReason;
    }

    public String getDangerLevelReason()
    {
        return dangerLevelReason;
    }
    public void setMeasures(String measures)
    {
        this.measures = measures;
    }

    public String getMeasures()
    {
        return measures;
    }
    public void setLawId(String lawId)
    {
        this.lawId = lawId;
    }

    public String getLawId()
    {
        return lawId;
    }
    public void setSolution(String solution)
    {
        this.solution = solution;
    }

    public String getSolution()
    {
        return solution;
    }
    public void setPicture1(String picture1)
    {
        this.picture1 = picture1;
    }

    public String getPicture1()
    {
        return picture1;
    }
    public void setPicture2(String picture2)
    {
        this.picture2 = picture2;
    }

    public String getPicture2()
    {
        return picture2;
    }
    public void setPicture3(String picture3)
    {
        this.picture3 = picture3;
    }

    public String getPicture3()
    {
        return picture3;
    }
    public void setIsDeleted(String isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted()
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("belongListId", getBelongListId())
                .append("speciesInfo", getSpeciesInfo())
                .append("dangerLevel", getDangerLevel())
                .append("dangerLevelReason", getDangerLevelReason())
                .append("measures", getMeasures())
                .append("lawId", getLawId())
                .append("solution", getSolution())
                .append("picture1", getPicture1())
                .append("picture2", getPicture2())
                .append("picture3", getPicture3())
                .append("isDeleted", getIsDeleted())
                .toString();
    }
}
