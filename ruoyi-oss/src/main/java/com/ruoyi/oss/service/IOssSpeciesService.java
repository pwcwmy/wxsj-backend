package com.ruoyi.oss.service;

import java.util.List;
import com.ruoyi.oss.domain.OssSpecies;

/**
 * 物种数据库信息Service接口
 * 
 * @author pwc
 * @date 2024-02-01
 */
public interface IOssSpeciesService 
{
    /**
     * 查询物种数据库信息
     * 
     * @param id 物种数据库信息主键
     * @return 物种数据库信息
     */
    public OssSpecies selectOssSpeciesById(Long id);

    /**
     * 查询物种数据库信息列表
     * 
     * @param ossSpecies 物种数据库信息
     * @return 物种数据库信息集合
     */
    public List<OssSpecies> selectOssSpeciesList(OssSpecies ossSpecies);

    /**
     * 新增物种数据库信息
     * 
     * @param ossSpecies 物种数据库信息
     * @return 结果
     */
    public int insertOssSpecies(OssSpecies ossSpecies);

    /**
     * 修改物种数据库信息
     * 
     * @param ossSpecies 物种数据库信息
     * @return 结果
     */
    public int updateOssSpecies(OssSpecies ossSpecies);

    /**
     * 批量删除物种数据库信息
     * 
     * @param ids 需要删除的物种数据库信息主键集合
     * @return 结果
     */
    public int deleteOssSpeciesByIds(Long[] ids);

    /**
     * 删除物种数据库信息信息
     * 
     * @param id 物种数据库信息主键
     * @return 结果
     */
    public int deleteOssSpeciesById(Long id);
}
