package com.company.project.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class SparkSessionVO {
    @ApiModelProperty(value = "sessionId")
    private String id;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "信息")
    private String appInfo;

    @ApiModelProperty(value = "类型")
    private String kind;

    @ApiModelProperty(value = "日志")
    private String[] log;

}
