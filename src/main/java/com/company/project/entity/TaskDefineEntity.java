package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 任务定义
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-02-04 15:17:55
 */
@Data
@TableName("task_define")
public class TaskDefineEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 任务id
	 */
	@TableId("id")
	private String id;

	/**
	 * 任务名称
	 */
	@TableField("task_name")
	private String taskName;

	/**
	 * 任务描述
	 */
	@TableField("task_desc")
	private String taskDesc;

	/**
	 * 任务参数
	 */
	@TableField("task_param")
	private String taskParam;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;


}
