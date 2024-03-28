package com.ruoyi.oss.mapper;

import java.util.List;
import com.ruoyi.oss.domain.OssSpecies;

/**
 * 物种数据库信息Mapper接口
 *
 * @author pwc
 * @date 2024-03-29
 */
public interface OssSpeciesMapper
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
     * 删除物种数据库信息
     *
     * @param id 物种数据库信息主键
     * @return 结果
     */
    public int deleteOssSpeciesById(Long id);

    /**
     * 批量删除物种数据库信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOssSpeciesByIds(Long[] ids);
}
