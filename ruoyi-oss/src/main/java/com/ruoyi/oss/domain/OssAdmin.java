package com.ruoyi.oss.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 登录用户信息对象 oss_admin
 * 
 * @author pwc
 * @date 2024-02-01
 */
public class OssAdmin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long adminId;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String adminPassword;

    /** 用户创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "用户创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adminCreateTime;

    /** 用户权限 */
    @Excel(name = "用户权限")
    private Long adminRole;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    private String isDeleted;

    public void setAdminId(Long adminId) 
    {
        this.adminId = adminId;
    }

    public Long getAdminId() 
    {
        return adminId;
    }
    public void setAdminPassword(String adminPassword) 
    {
        this.adminPassword = adminPassword;
    }

    public String getAdminPassword() 
    {
        return adminPassword;
    }
    public void setAdminCreateTime(Date adminCreateTime) 
    {
        this.adminCreateTime = adminCreateTime;
    }

    public Date getAdminCreateTime() 
    {
        return adminCreateTime;
    }
    public void setAdminRole(Long adminRole) 
    {
        this.adminRole = adminRole;
    }

    public Long getAdminRole() 
    {
        return adminRole;
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
            .append("adminId", getAdminId())
            .append("adminPassword", getAdminPassword())
            .append("adminCreateTime", getAdminCreateTime())
            .append("adminRole", getAdminRole())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
