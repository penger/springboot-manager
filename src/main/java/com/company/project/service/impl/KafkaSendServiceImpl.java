package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.KafkaSendMapper;
import com.company.project.entity.KafkaSendEntity;
import com.company.project.service.KafkaSendService;

import java.util.List;


@Service("kafkaSendService")
public class KafkaSendServiceImpl extends ServiceImpl<KafkaSendMapper, KafkaSendEntity> implements KafkaSendService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @Override
    public void send2Kafka(String json) {
        kafkaTemplate.send("test_kafka",json);
    }

    @Override
    public void sendMsg2KafkaByIds(List<String> ids) {
        List<KafkaSendEntity> entities = baseMapper.selectBatchIds(ids);
        for (KafkaSendEntity item : entities) {
            Integer times = item.getTimes();
            for (Integer i = 0; i < times; i++) {
                item.setTimes(i+1);
                String json = JSON.toJSONString(item);
                send2Kafka(json);
            }
        }
    }
}