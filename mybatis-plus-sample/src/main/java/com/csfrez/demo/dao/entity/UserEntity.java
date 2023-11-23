package com.csfrez.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.csfrez.demo.enums.SexEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "user")
public class UserEntity {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    @TableField(value = "nickname", condition = SqlCondition.EQUAL)
    private String nickname;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private SexEnum sex;

    /**
     * 版本
     */
    @TableField(value = "version", update = "%s+1")
    private Integer version;

    /**
     * 时间字段，自动添加
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
