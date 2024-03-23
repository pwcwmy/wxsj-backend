package com.ruoyi.oss.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.oss.mapper.OssConfigMapper;
import com.ruoyi.oss.domain.OssConfig;
import com.ruoyi.oss.service.IOssConfigService;

/**
 * 对象存储配置
Service业务层处理
 * 
 * @author pwc
 * @date 2024-02-01
 */
@Service
public class OssConfigServiceImpl implements IOssConfigService 
{
    @Autowired
    private OssConfigMapper ossConfigMapper;

    /**
     * 查询对象存储配置

     * 
     * @param configName 对象存储配置
主键
     * @return 对象存储配置

     */
    @Override
    public OssConfig selectOssConfigByConfigName(String configName)
    {
        return ossConfigMapper.selectOssConfigByConfigName(configName);
    }

    /**
     * 查询对象存储配置
列表
     * 
     * @param ossConfig 对象存储配置

     * @return 对象存储配置

     */
    @Override
    public List<OssConfig> selectOssConfigList(OssConfig ossConfig)
    {
        return ossConfigMapper.selectOssConfigList(ossConfig);
    }

    /**
     * 新增对象存储配置

     * 
     * @param ossConfig 对象存储配置

     * @return 结果
     */
    @Override
    public int insertOssConfig(OssConfig ossConfig)
    {
        return ossConfigMapper.insertOssConfig(ossConfig);
    }

    /**
     * 修改对象存储配置

     * 
     * @param ossConfig 对象存储配置

     * @return 结果
     */
    @Override
    public int updateOssConfig(OssConfig ossConfig)
    {
        return ossConfigMapper.updateOssConfig(ossConfig);
    }

    /**
     * 批量删除对象存储配置

     * 
     * @param configNames 需要删除的对象存储配置
主键
     * @return 结果
     */
    @Override
    public int deleteOssConfigByConfigNames(String[] configNames)
    {
        return ossConfigMapper.deleteOssConfigByConfigNames(configNames);
    }

    /**
     * 删除对象存储配置
信息
     * 
     * @param configName 对象存储配置
主键
     * @return 结果
     */
    @Override
    public int deleteOssConfigByConfigName(String configName)
    {
        return ossConfigMapper.deleteOssConfigByConfigName(configName);
    }
}
