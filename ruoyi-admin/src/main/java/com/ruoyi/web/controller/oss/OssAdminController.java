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
import com.ruoyi.oss.domain.OssAdmin;
import com.ruoyi.oss.service.IOssAdminService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 登录用户信息Controller
 * 
 * @author pwc
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/oss/admin")
public class OssAdminController extends BaseController
{
    @Autowired
    private IOssAdminService ossAdminService;

    /**
     * 查询登录用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('oss:admin:list')")
    @GetMapping("/list")
    public TableDataInfo list(OssAdmin ossAdmin)
    {
        startPage();
        List<OssAdmin> list = ossAdminService.selectOssAdminList(ossAdmin);
        return getDataTable(list);
    }

    /**
     * 导出登录用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('oss:admin:export')")
    @Log(title = "登录用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OssAdmin ossAdmin)
    {
        List<OssAdmin> list = ossAdminService.selectOssAdminList(ossAdmin);
        ExcelUtil<OssAdmin> util = new ExcelUtil<OssAdmin>(OssAdmin.class);
        util.exportExcel(response, list, "登录用户信息数据");
    }

    /**
     * 获取登录用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('oss:admin:query')")
    @GetMapping(value = "/{adminId}")
    public AjaxResult getInfo(@PathVariable("adminId") Long adminId)
    {
        return success(ossAdminService.selectOssAdminByAdminId(adminId));
    }

    /**
     * 新增登录用户信息
     */
    @PreAuthorize("@ss.hasPermi('oss:admin:add')")
    @Log(title = "登录用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OssAdmin ossAdmin)
    {
        return toAjax(ossAdminService.insertOssAdmin(ossAdmin));
    }

    /**
     * 修改登录用户信息
     */
    @PreAuthorize("@ss.hasPermi('oss:admin:edit')")
    @Log(title = "登录用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OssAdmin ossAdmin)
    {
        return toAjax(ossAdminService.updateOssAdmin(ossAdmin));
    }

    /**
     * 删除登录用户信息
     */
    @PreAuthorize("@ss.hasPermi('oss:admin:remove')")
    @Log(title = "登录用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{adminIds}")
    public AjaxResult remove(@PathVariable Long[] adminIds)
    {
        return toAjax(ossAdminService.deleteOssAdminByAdminIds(adminIds));
    }
}
