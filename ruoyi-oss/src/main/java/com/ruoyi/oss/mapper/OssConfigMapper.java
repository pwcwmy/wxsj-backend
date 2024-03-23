package com.ruoyi.oss.mapper;

import java.util.List;
import com.ruoyi.oss.domain.OssConfig;

/**
 * 对象存储配置
Mapper接口
 * 
 * @author pwc
 * @date 2024-02-01
 */
public interface OssConfigMapper 
{
    /**
     * 查询对象存储配置

     * 
     * @param configName 对象存储配置
主键
     * @return 对象存储配置

     */
    public OssConfig selectOssConfigByConfigName(String configName);

    /**
     * 查询对象存储配置
列表
     * 
     * @param ossConfig 对象存储配置

     * @return 对象存储配置
集合
     */
    public List<OssConfig> selectOssConfigList(OssConfig ossConfig);

    /**
     * 新增对象存储配置

     * 
     * @param ossConfig 对象存储配置

     * @return 结果
     */
    public int insertOssConfig(OssConfig ossConfig);

    /**
     * 修改对象存储配置

     * 
     * @param ossConfig 对象存储配置

     * @return 结果
     */
    public int updateOssConfig(OssConfig ossConfig);

    /**
     * 删除对象存储配置

     * 
     * @param configName 对象存储配置
主键
     * @return 结果
     */
    public int deleteOssConfigByConfigName(String configName);

    /**
     * 批量删除对象存储配置

     * 
     * @param configNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOssConfigByConfigNames(String[] configNames);
}