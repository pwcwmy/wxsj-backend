package com.ruoyi.oss.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 对象存储配置
对象 oss_config
 * 
 * @author pwc
 * @date 2024-02-01
 */
public class OssConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置属性名 */
    @Excel(name = "配置属性名")
    private String configName;

    /** 配置属性值 */
    @Excel(name = "配置属性值")
    private String configValue;

    /** oss */
    @Excel(name = "oss")
    private String configTeam;

    public void setConfigName(String configName) 
    {
        this.configName = configName;
    }

    public String getConfigName() 
    {
        return configName;
    }
    public void setConfigValue(String configValue) 
    {
        this.configValue = configValue;
    }

    public String getConfigValue() 
    {
        return configValue;
    }
    public void setConfigTeam(String configTeam) 
    {
        this.configTeam = configTeam;
    }

    public String getConfigTeam() 
    {
        return configTeam;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("configName", getConfigName())
            .append("configValue", getConfigValue())
            .append("configTeam", getConfigTeam())
            .toString();
    }
}
