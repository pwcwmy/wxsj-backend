package com.ruoyi.law.mapper;

import java.util.List;
import com.ruoyi.law.domain.Law;
import org.springframework.stereotype.Repository;

/**
 * 法律知识图谱Mapper接口
 * 
 * @author pwc
 * @date 2024-01-31
 */
@Repository
public interface LawMapper 
{
    /**
     * 查询法律知识图谱
     * 
     * @param id 法律知识图谱主键
     * @return 法律知识图谱
     */
    public Law selectLawById(Long id);

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
     * 删除法律知识图谱
     * 
     * @param id 法律知识图谱主键
     * @return 结果
     */
    public int deleteLawById(Long id);

    /**
     * 批量删除法律知识图谱
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLawByIds(Long[] ids);
}
