package com.ruoyi.oss.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.oss.mapper.OssSpeciesMapper;
import com.ruoyi.oss.domain.OssSpecies;
import com.ruoyi.oss.service.IOssSpeciesService;

/**
 * 物种数据库信息Service业务层处理
 * 
 * @author pwc
 * @date 2024-02-01
 */
@Service
public class OssSpeciesServiceImpl implements IOssSpeciesService 
{
    @Autowired
    private OssSpeciesMapper ossSpeciesMapper;

    /**
     * 查询物种数据库信息
     * 
     * @param id 物种数据库信息主键
     * @return 物种数据库信息
     */
    @Override
    public OssSpecies selectOssSpeciesById(Long id)
    {
        return ossSpeciesMapper.selectOssSpeciesById(id);
    }

    /**
     * 查询物种数据库信息列表
     * 
     * @param ossSpecies 物种数据库信息
     * @return 物种数据库信息
     */
    @Override
    public List<OssSpecies> selectOssSpeciesList(OssSpecies ossSpecies)
    {
        return ossSpeciesMapper.selectOssSpeciesList(ossSpecies);
    }

    /**
     * 新增物种数据库信息
     * 
     * @param ossSpecies 物种数据库信息
     * @return 结果
     */
    @Override
    public int insertOssSpecies(OssSpecies ossSpecies)
    {
        return ossSpeciesMapper.insertOssSpecies(ossSpecies);
    }

    /**
     * 修改物种数据库信息
     * 
     * @param ossSpecies 物种数据库信息
     * @return 结果
     */
    @Override
    public int updateOssSpecies(OssSpecies ossSpecies)
    {
        return ossSpeciesMapper.updateOssSpecies(ossSpecies);
    }

    /**
     * 批量删除物种数据库信息
     * 
     * @param ids 需要删除的物种数据库信息主键
     * @return 结果
     */
    @Override
    public int deleteOssSpeciesByIds(Long[] ids)
    {
        return ossSpeciesMapper.deleteOssSpeciesByIds(ids);
    }

    /**
     * 删除物种数据库信息信息
     * 
     * @param id 物种数据库信息主键
     * @return 结果
     */
    @Override
    public int deleteOssSpeciesById(Long id)
    {
        return ossSpeciesMapper.deleteOssSpeciesById(id);
    }
}
