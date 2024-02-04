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
 * Kafka发送任务
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-02-04 16:17:19
 */
@Data
@TableName("kafka_send")
public class KafkaSendEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 任务id
	 */
	@TableId("id")
	private String id;

	/**
	 * 标题
	 */
	@TableField("title")
	private String title;

	@TableField("topic_name")
	private String topicName;

	/**
	 * 类型
	 */
	@TableField("type")
	private String type;

	/**
	 * 内容
	 */
	@TableField("content")
	private String content;

	/**
	 * 次数
	 */
	@TableField("times")
	private Integer times;

	/**
	 * 创建时间
	 */
	@TableField( value = "create_time" , fill = FieldFill.INSERT)
	private Date createTime;


}
