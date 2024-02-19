package com.company.project.vo.resp;

import lombok.Data;

import java.util.List;

@Data
public class SparkSessionPageVO {

    private List<SparkSessionVO> sessions;
    private Integer from ;
    private Integer total;

}
