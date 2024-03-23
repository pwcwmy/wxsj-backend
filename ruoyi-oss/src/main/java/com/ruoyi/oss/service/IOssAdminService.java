package com.ruoyi.oss.service;

import java.util.List;
import com.ruoyi.oss.domain.OssAdmin;

/**
 * 登录用户信息Service接口
 * 
 * @author pwc
 * @date 2024-02-01
 */
public interface IOssAdminService 
{
    /**
     * 查询登录用户信息
     * 
     * @param adminId 登录用户信息主键
     * @return 登录用户信息
     */
    public OssAdmin selectOssAdminByAdminId(Long adminId);

    /**
     * 查询登录用户信息列表
     * 
     * @param ossAdmin 登录用户信息
     * @return 登录用户信息集合
     */
    public List<OssAdmin> selectOssAdminList(OssAdmin ossAdmin);

    /**
     * 新增登录用户信息
     * 
     * @param ossAdmin 登录用户信息
     * @return 结果
     */
    public int insertOssAdmin(OssAdmin ossAdmin);

    /**
     * 修改登录用户信息
     * 
     * @param ossAdmin 登录用户信息
     * @return 结果
     */
    public int updateOssAdmin(OssAdmin ossAdmin);

    /**
     * 批量删除登录用户信息
     * 
     * @param adminIds 需要删除的登录用户信息主键集合
     * @return 结果
     */
    public int deleteOssAdminByAdminIds(Long[] adminIds);

    /**
     * 删除登录用户信息信息
     * 
     * @param adminId 登录用户信息主键
     * @return 结果
     */
    public int deleteOssAdminByAdminId(Long adminId);
}
