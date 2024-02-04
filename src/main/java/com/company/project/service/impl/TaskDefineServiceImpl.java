package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.TaskDefineMapper;
import com.company.project.entity.TaskDefineEntity;
import com.company.project.service.TaskDefineService;


@Service("taskDefineService")
public class TaskDefineServiceImpl extends ServiceImpl<TaskDefineMapper, TaskDefineEntity> implements TaskDefineService {


}