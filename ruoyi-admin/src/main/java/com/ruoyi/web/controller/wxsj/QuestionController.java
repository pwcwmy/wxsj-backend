package com.ruoyi.web.controller.wxsj;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.law.domain.Question;
import com.ruoyi.law.service.IQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 问卷答案组合Controller
 * 
 * @author pwc
 * @date 2024-04-13
 */
@RestController
@RequestMapping("/wxsj/question")
public class QuestionController extends BaseController
{
    @Autowired
    private IQuestionService questionService;

    /**
     * 查询问卷答案组合列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(Question question)
    {
        startPage();
        List<Question> list = questionService.selectQuestionList(question);
        return getDataTable(list);
    }

    /**
     * 导出问卷答案组合列表
     */
    @PreAuthorize("@ss.hasPermi('wxsj:question:export')")
    @Log(title = "问卷答案组合", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Question question)
    {
        List<Question> list = questionService.selectQuestionList(question);
        ExcelUtil<Question> util = new ExcelUtil<Question>(Question.class);
        util.exportExcel(response, list, "问卷答案组合数据");
    }

    /**
     * 获取问卷答案组合详细信息
     */
    @PreAuthorize("@ss.hasPermi('wxsj:question:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(questionService.selectQuestionById(id));
    }

    /**
     * 新增问卷答案组合
     */
    @PreAuthorize("@ss.hasPermi('wxsj:question:add')")
    @Log(title = "问卷答案组合", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Question question)
    {
        return toAjax(questionService.insertQuestion(question));
    }

    /**
     * 修改问卷答案组合
     */
    @PreAuthorize("@ss.hasPermi('wxsj:question:edit')")
    @Log(title = "问卷答案组合", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Question question)
    {
        return toAjax(questionService.updateQuestion(question));
    }

    /**
     * 删除问卷答案组合
     */
    @PreAuthorize("@ss.hasPermi('wxsj:question:remove')")
    @Log(title = "问卷答案组合", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(questionService.deleteQuestionByIds(ids));
    }
}
