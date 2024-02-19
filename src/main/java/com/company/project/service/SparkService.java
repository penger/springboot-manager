package com.company.project.service;

import com.company.project.vo.resp.SparkSessionPageVO;

/**
 * 任务定义
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-02-04 15:17:55
 */
public interface SparkService {

    SparkSessionPageVO getSessionList();

    String submitSparkCodeAsync(String sessionCode, String code);

    String submitSparkSync(String sessionCode, String code);

    String getSubmittedSparkCodeResult(String sessionCode, String returnCode);


}

