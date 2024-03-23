package com.ruoyi.web.controller.wxsj;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.law.domain.Key;
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
import com.ruoyi.law.domain.Law;
import com.ruoyi.law.service.ILawService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 法律知识图谱Controller
 *
 * @author pwc
 * @date 2024-01-31
 */
@RestController
@RequestMapping("/wxsj/law")
public class LawController extends BaseController {
    @Autowired
    private ILawService lawService;

    /**
     * 查询法律知识图谱列表
     * url: '/wxsj/law/list',
     *     method: 'get',
     *     params: query
     */
    @PreAuthorize("@ss.hasPermi('wxsj:law:list')")
    @GetMapping("/list")
    public TableDataInfo list(Law law) {
        startPage();
        List<Law> list = lawService.selectLawList(law);
        return getDataTable(list);
    }

    /**
     * 导出法律知识图谱列表
     */
    @PreAuthorize("@ss.hasPermi('wxsj:law:export')")
    @Log(title = "法律知识图谱", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Law law) {
        List<Law> list = lawService.selectLawList(law);
        ExcelUtil<Law> util = new ExcelUtil<Law>(Law.class);
        util.exportExcel(response, list, "法律知识图谱数据");
    }

    /**
     * 获取法律知识图谱详细信息
     */
    @PreAuthorize("@ss.hasPermi('wxsj:law:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(lawService.selectLawById(id));
    }

    /**
     * 搜索引擎
     */
    @PreAuthorize("@ss.hasPermi('wxsj:law:search')")
    @Log(title = "法律知识图谱", businessType = BusinessType.DELETE)
    @PostMapping("search")
    public AjaxResult search(@RequestBody Key keys) {
        return success(lawService.selectLawByKeys(keys));
    }

    /**
     * 新增法律知识图谱
     */
    @PreAuthorize("@ss.hasPermi('wxsj:law:add')")
    @Log(title = "法律知识图谱", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Law law) {
        return toAjax(lawService.insertLaw(law));
    }

    /**
     * 修改法律知识图谱
     */
    @PreAuthorize("@ss.hasPermi('wxsj:law:edit')")
    @Log(title = "法律知识图谱", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Law law) {
        return toAjax(lawService.updateLaw(law));
    }

    /**
     * 删除法律知识图谱
     */
    @PreAuthorize("@ss.hasPermi('wxsj:law:remove')")
    @Log(title = "法律知识图谱", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(lawService.deleteLawByIds(ids));
    }

}
