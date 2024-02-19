package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.common.utils.DataResult;
import com.company.project.entity.TaskDefineEntity;
import com.company.project.service.SparkService;
import com.company.project.service.TaskDefineService;
import com.company.project.vo.req.SparkSQLSubmitReqVO;
import com.company.project.vo.resp.SparkSessionPageVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 任务定义
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-02-04 15:17:55
 */
@Controller
@RequestMapping("/")
public class SparkController {
    @Autowired
    private SparkService sparkService;



    @GetMapping("/index/spark")
    public String toSpark() {
        return "spark/list";
    }

    /**
    * 跳转到页面
    */
    @GetMapping("/spark/sessions")
    @ResponseBody
    public DataResult listSession() {
        SparkSessionPageVO sessionPageVO = sparkService.getSessionList();
        return  DataResult.success(sessionPageVO);
    }

    @GetMapping("/spark/createSession")
    @ResponseBody
    public DataResult createSession() {
        String result = sparkService.createSession();
        return  DataResult.success(result);
    }

    @GetMapping("/spark/deleteSession")
    @ResponseBody
    public DataResult deletesion(@RequestParam("id") String id) {
        Boolean isDeleted = sparkService.deleteSession(id);
        return  DataResult.success(isDeleted);
    }


    @PostMapping("/spark/submit")
    @ResponseBody
    public DataResult submitTask(@RequestBody SparkSQLSubmitReqVO vo){
        String result = sparkService.submitSparkSync(vo.getSessionID(), vo.getCode());
        return DataResult.success(result);
    }




//    @ApiOperation(value = "新增")
//    @PostMapping("taskDefine/add")
//    @RequiresPermissions("taskDefine:add")
//    @ResponseBody
//    public DataResult add(@RequestBody TaskDefineEntity taskDefine){
//        taskDefineService.save(taskDefine);
//        return DataResult.success();
//    }
//
//    @ApiOperation(value = "删除")
//    @DeleteMapping("taskDefine/delete")
//    @RequiresPermissions("taskDefine:delete")
//    @ResponseBody
//    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
//        taskDefineService.removeByIds(ids);
//        return DataResult.success();
//    }
//
//    @ApiOperation(value = "更新")
//    @PutMapping("taskDefine/update")
//    @RequiresPermissions("taskDefine:update")
//    @ResponseBody
//    public DataResult update(@RequestBody TaskDefineEntity taskDefine){
//        taskDefineService.updateById(taskDefine);
//        return DataResult.success();
//    }
//
//    @ApiOperation(value = "查询分页数据")
//    @PostMapping("taskDefine/listByPage")
//    @RequiresPermissions("taskDefine:list")
//    @ResponseBody
//    public DataResult findListByPage(@RequestBody TaskDefineEntity taskDefine){
//        Page page = new Page(taskDefine.getPage(), taskDefine.getLimit());
//        LambdaQueryWrapper<TaskDefineEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        if(StringUtils.isNotBlank(taskDefine.getTaskName())){
//            queryWrapper.like(TaskDefineEntity::getTaskName ,taskDefine.getTaskName());
//        }
//        //queryWrapper.eq(TaskDefineEntity::getId, taskDefine.getId());
//        IPage<TaskDefineEntity> iPage = taskDefineService.page(page, queryWrapper);
//        return DataResult.success(iPage);
//    }

}
