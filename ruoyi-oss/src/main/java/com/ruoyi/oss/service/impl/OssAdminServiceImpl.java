package com.ruoyi.oss.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.oss.mapper.OssAdminMapper;
import com.ruoyi.oss.domain.OssAdmin;
import com.ruoyi.oss.service.IOssAdminService;

/**
 * 登录用户信息Service业务层处理
 * 
 * @author pwc
 * @date 2024-02-01
 */
@Service
public class OssAdminServiceImpl implements IOssAdminService 
{
    @Autowired
    private OssAdminMapper ossAdminMapper;

    /**
     * 查询登录用户信息
     * 
     * @param adminId 登录用户信息主键
     * @return 登录用户信息
     */
    @Override
    public OssAdmin selectOssAdminByAdminId(Long adminId)
    {
        return ossAdminMapper.selectOssAdminByAdminId(adminId);
    }

    /**
     * 查询登录用户信息列表
     * 
     * @param ossAdmin 登录用户信息
     * @return 登录用户信息
     */
    @Override
    public List<OssAdmin> selectOssAdminList(OssAdmin ossAdmin)
    {
        return ossAdminMapper.selectOssAdminList(ossAdmin);
    }

    /**
     * 新增登录用户信息
     * 
     * @param ossAdmin 登录用户信息
     * @return 结果
     */
    @Override
    public int insertOssAdmin(OssAdmin ossAdmin)
    {
        return ossAdminMapper.insertOssAdmin(ossAdmin);
    }

    /**
     * 修改登录用户信息
     * 
     * @param ossAdmin 登录用户信息
     * @return 结果
     */
    @Override
    public int updateOssAdmin(OssAdmin ossAdmin)
    {
        return ossAdminMapper.updateOssAdmin(ossAdmin);
    }

    /**
     * 批量删除登录用户信息
     * 
     * @param adminIds 需要删除的登录用户信息主键
     * @return 结果
     */
    @Override
    public int deleteOssAdminByAdminIds(Long[] adminIds)
    {
        return ossAdminMapper.deleteOssAdminByAdminIds(adminIds);
    }

    /**
     * 删除登录用户信息信息
     * 
     * @param adminId 登录用户信息主键
     * @return 结果
     */
    @Override
    public int deleteOssAdminByAdminId(Long adminId)
    {
        return ossAdminMapper.deleteOssAdminByAdminId(adminId);
    }
}
