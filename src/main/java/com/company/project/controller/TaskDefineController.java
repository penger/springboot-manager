package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.TaskDefineEntity;
import com.company.project.service.TaskDefineService;



/**
 * 任务定义
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-02-04 15:17:55
 */
@Controller
@RequestMapping("/")
public class TaskDefineController {
    @Autowired
    private TaskDefineService taskDefineService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/taskDefine")
    public String taskDefine() {
        return "taskdefine/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("taskDefine/add")
    @RequiresPermissions("taskDefine:add")
    @ResponseBody
    public DataResult add(@RequestBody TaskDefineEntity taskDefine){
        taskDefineService.save(taskDefine);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("taskDefine/delete")
    @RequiresPermissions("taskDefine:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        taskDefineService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("taskDefine/update")
    @RequiresPermissions("taskDefine:update")
    @ResponseBody
    public DataResult update(@RequestBody TaskDefineEntity taskDefine){
        taskDefineService.updateById(taskDefine);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("taskDefine/listByPage")
    @RequiresPermissions("taskDefine:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody TaskDefineEntity taskDefine){
        Page page = new Page(taskDefine.getPage(), taskDefine.getLimit());
        LambdaQueryWrapper<TaskDefineEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(TaskDefineEntity::getId, taskDefine.getId());
        IPage<TaskDefineEntity> iPage = taskDefineService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
