package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.KafkaSendEntity;
import com.company.project.service.KafkaSendService;



/**
 * Kafka发送任务
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-02-04 16:17:19
 */
@Controller
@RequestMapping("/")
public class KafkaSendController {
    @Autowired
    private KafkaSendService kafkaSendService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/kafkaSend")
    public String kafkaSend() {
        return "kafkasend/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("kafkaSend/add")
    @RequiresPermissions("kafkaSend:add")
    @ResponseBody
    public DataResult add(@RequestBody KafkaSendEntity kafkaSend){
        kafkaSendService.save(kafkaSend);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("kafkaSend/delete")
    @RequiresPermissions("kafkaSend:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        kafkaSendService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "按id来发送kafka消息")
    @ResponseBody
    @PostMapping("kafkaSend/send")
    public DataResult send(@RequestBody @ApiParam(value = "id集合") List<String> ids){
//        System.out.println(ids);
        kafkaSendService.sendMsg2KafkaByIds(ids);
        System.out.println("发送数据到kafka:"+ids);
        return DataResult.success();
    }





    @ApiOperation(value = "更新")
    @PutMapping("kafkaSend/update")
    @RequiresPermissions("kafkaSend:update")
    @ResponseBody
    public DataResult update(@RequestBody KafkaSendEntity kafkaSend){
        kafkaSendService.updateById(kafkaSend);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("kafkaSend/listByPage")
    @RequiresPermissions("kafkaSend:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody KafkaSendEntity kafkaSend){
        Page page = new Page(kafkaSend.getPage(), kafkaSend.getLimit());
        LambdaQueryWrapper<KafkaSendEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(KafkaSendEntity::getId, kafkaSend.getId());
        if (!StringUtils.isEmpty(kafkaSend.getContent())) {
            queryWrapper.eq(KafkaSendEntity::getContent, kafkaSend.getContent());
        }
        IPage<KafkaSendEntity> iPage = kafkaSendService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
