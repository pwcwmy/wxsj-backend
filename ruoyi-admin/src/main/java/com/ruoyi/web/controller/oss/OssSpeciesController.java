package com.ruoyi.web.controller.oss;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.oss.domain.OssSpecies;
import com.ruoyi.oss.service.IOssSpeciesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物种数据库信息Controller
 *
 * @author pwc
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/oss/species")
public class OssSpeciesController extends BaseController
{
    @Autowired
    private IOssSpeciesService ossSpeciesService;

    /**
     * 查询物种数据库信息列表
     */
    @PreAuthorize("@ss.hasPermi('oss:species:list')")
    @GetMapping("/list")
    public TableDataInfo list(OssSpecies ossSpecies)
    {
        startPage();
        List<OssSpecies> list = ossSpeciesService.selectOssSpeciesList(ossSpecies);
        return getDataTable(list);
    }

    /**
     * 导出物种数据库信息列表
     */
    @PreAuthorize("@ss.hasPermi('oss:species:export')")
    @Log(title = "物种数据库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OssSpecies ossSpecies)
    {
        List<OssSpecies> list = ossSpeciesService.selectOssSpeciesList(ossSpecies);
        ExcelUtil<OssSpecies> util = new ExcelUtil<OssSpecies>(OssSpecies.class);
        util.exportExcel(response, list, "物种数据库信息数据");
    }

    /**
     * 获取物种数据库信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('oss:species:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ossSpeciesService.selectOssSpeciesById(id));
    }

    /**
     * 新增物种数据库信息
     */
    @PreAuthorize("@ss.hasPermi('oss:species:add')")
    @Log(title = "物种数据库信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OssSpecies ossSpecies)
    {
        return toAjax(ossSpeciesService.insertOssSpecies(ossSpecies));
    }

    /**
     * 修改物种数据库信息
     */
    @PreAuthorize("@ss.hasPermi('oss:species:edit')")
    @Log(title = "物种数据库信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OssSpecies ossSpecies)
    {
        return toAjax(ossSpeciesService.updateOssSpecies(ossSpecies));
    }

    /**
     * 删除物种数据库信息
     */
    @PreAuthorize("@ss.hasPermi('oss:species:remove')")
    @Log(title = "物种数据库信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ossSpeciesService.deleteOssSpeciesByIds(ids));
    }
}
