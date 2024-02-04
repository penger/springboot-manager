package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.KafkaSendEntity;

import java.util.List;

/**
 * Kafka发送任务
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-02-04 16:17:19
 */
public interface KafkaSendService extends IService<KafkaSendEntity> {

    void send2Kafka(String json);


    void sendMsg2KafkaByIds(List<String> ids);
}

