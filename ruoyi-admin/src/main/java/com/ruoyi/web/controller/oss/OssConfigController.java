package com.ruoyi.web.controller.oss;

import java.util.List;

import com.ruoyi.common.enums.BusinessType;
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
import com.ruoyi.oss.domain.OssConfig;
import com.ruoyi.oss.service.IOssConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 对象存储配置
Controller
 * 
 * @author pwc
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/oss/config")
public class OssConfigController extends BaseController
{
    @Autowired
    private IOssConfigService ossConfigService;

    /**
     * 查询对象存储配置
列表
     */
    @PreAuthorize("@ss.hasPermi('oss:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(OssConfig ossConfig)
    {
        startPage();
        List<OssConfig> list = ossConfigService.selectOssConfigList(ossConfig);
        return getDataTable(list);
    }

    /**
     * 导出对象存储配置
列表
     */
    @PreAuthorize("@ss.hasPermi('oss:config:export')")
    @Log(title = "对象存储配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OssConfig ossConfig)
    {
        List<OssConfig> list = ossConfigService.selectOssConfigList(ossConfig);
        ExcelUtil<OssConfig> util = new ExcelUtil<OssConfig>(OssConfig.class);
        util.exportExcel(response, list, "对象存储配置数据");
    }

    /**
     * 获取对象存储配置
详细信息
     */
    @PreAuthorize("@ss.hasPermi('oss:config:query')")
    @GetMapping(value = "/{configName}")
    public AjaxResult getInfo(@PathVariable("configName") String configName)
    {
        return success(ossConfigService.selectOssConfigByConfigName(configName));
    }

    /**
     * 新增对象存储配置

     */
    @PreAuthorize("@ss.hasPermi('oss:config:add')")
    @Log(title = "对象存储配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OssConfig ossConfig)
    {
        return toAjax(ossConfigService.insertOssConfig(ossConfig));
    }

    /**
     * 修改对象存储配置

     */
    @PreAuthorize("@ss.hasPermi('oss:config:edit')")
    @Log(title = "对象存储配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OssConfig ossConfig)
    {
        return toAjax(ossConfigService.updateOssConfig(ossConfig));
    }

    /**
     * 删除对象存储配置

     */
    @PreAuthorize("@ss.hasPermi('oss:config:remove')")
    @Log(title = "对象存储配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{configNames}")
    public AjaxResult remove(@PathVariable String[] configNames)
    {
        return toAjax(ossConfigService.deleteOssConfigByConfigNames(configNames));
    }
}
