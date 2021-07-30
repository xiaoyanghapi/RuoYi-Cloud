package com.ruoyi.test.student.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.test.student.domain.SysStudent;
import com.ruoyi.test.student.service.ISysStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 学生信息Controller
 * 
 * @author ruoyi
 * @date 2021-07-31
 */
@RestController
@RequestMapping("/student")
public class SysStudentController extends BaseController
{
    @Autowired
    private ISysStudentService sysStudentService;

    /**
     * 查询学生信息列表
     */
    @PreAuthorize(hasPermi = "test:student:list")
    @GetMapping("/list")
    public TableDataInfo list(SysStudent sysStudent)
    {
        startPage();
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生信息列表
     */
    @PreAuthorize(hasPermi = "system:student:export")
    @Log(title = "学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysStudent sysStudent) throws IOException
    {
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        util.exportExcel(response, list, "学生信息数据");
    }

    /**
     * 获取学生信息详细信息
     */
    @PreAuthorize(hasPermi = "system:student:query")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return AjaxResult.success(sysStudentService.selectSysStudentByStudentId(studentId));
    }

    /**
     * 新增学生信息
     */
    @PreAuthorize(hasPermi = "system:student:add")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.insertSysStudent(sysStudent));
    }

    /**
     * 修改学生信息
     */
    @PreAuthorize(hasPermi = "system:student:edit")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.updateSysStudent(sysStudent));
    }

    /**
     * 删除学生信息
     */
    @PreAuthorize(hasPermi = "system:student:remove")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(sysStudentService.deleteSysStudentByStudentIds(studentIds));
    }
}
