package com.ruoyi.law.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.law.domain.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.law.mapper.LawMapper;
import com.ruoyi.law.domain.Law;
import com.ruoyi.law.service.ILawService;

/**
 * 法律知识图谱Service业务层处理
 * 
 * @author pwc
 * @date 2024-01-31
 */
@Service
public class LawServiceImpl implements ILawService 
{
    @Autowired
    private LawMapper lawMapper;

    /**
     * 查询法律知识图谱
     * 
     * @param id 法律知识图谱主键
     * @return 法律知识图谱
     */
    @Override
    public Law selectLawById(Long id)
    {
        return lawMapper.selectLawById(id);
    }

    /**
     * 查询法律知识图谱列表
     * 
     * @param law 法律知识图谱
     * @return 法律知识图谱
     */
    @Override
    public List<Law> selectLawList(Law law)
    {
        return lawMapper.selectLawList(law);
    }

    /**
     * 新增法律知识图谱
     * 
     * @param law 法律知识图谱
     * @return 结果
     */
    @Override
    public int insertLaw(Law law)
    {
        law.setCreateTime(DateUtils.getNowDate());
        return lawMapper.insertLaw(law);
    }

    /**
     * 修改法律知识图谱
     * 
     * @param law 法律知识图谱
     * @return 结果
     */
    @Override
    public int updateLaw(Law law)
    {
        law.setUpdateTime(DateUtils.getNowDate());
        return lawMapper.updateLaw(law);
    }

    /**
     * 批量删除法律知识图谱
     * 
     * @param ids 需要删除的法律知识图谱主键
     * @return 结果
     */
    @Override
    public int deleteLawByIds(Long[] ids)
    {
        return lawMapper.deleteLawByIds(ids);
    }

    /**
     * 删除法律知识图谱信息
     * 
     * @param id 法律知识图谱主键
     * @return 结果
     */
    @Override
    public int deleteLawById(Long id)
    {
        return lawMapper.deleteLawById(id);
    }

    @Override
    public String selectLawByKeys(Key keys) {
        return null;
    }
}
