package com.ruoyi.law.service;

import java.util.List;

import com.ruoyi.law.domain.Key;
import com.ruoyi.law.domain.Law;

/**
 * 法律知识图谱Service接口
 * 
 * @author pwc
 * @date 2024-01-31
 */
public interface ILawService 
{
    /**
     * 根据主键id查询法律知识图谱
     * 
     * @param id 法律知识图谱主键
     * @return 法律知识图谱
     */
    public Law selectLawById(Long id);

    /**
     * 根据keys查询法律知识图谱
     *
     * @param keys 法律知识图谱关键词
     * @return 法律知识图谱
     */
    public Law selectLawByKeys(Key keys);

    /**
     * 查询法律知识图谱列表
     * 
     * @param law 法律知识图谱
     * @return 法律知识图谱集合
     */
    public List<Law> selectLawList(Law law);

    /**
     * 新增法律知识图谱
     * 
     * @param law 法律知识图谱
     * @return 结果
     */
    public int insertLaw(Law law);

    /**
     * 修改法律知识图谱
     * 
     * @param law 法律知识图谱
     * @return 结果
     */
    public int updateLaw(Law law);

    /**
     * 批量删除法律知识图谱
     * 
     * @param ids 需要删除的法律知识图谱主键集合
     * @return 结果
     */
    public int deleteLawByIds(Long[] ids);

    /**
     * 删除法律知识图谱信息
     * 
     * @param id 法律知识图谱主键
     * @return 结果
     */
    public int deleteLawById(Long id);

}
