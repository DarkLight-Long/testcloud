package org.testcloud.modules.consumer.DemoConsumer.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {

    private Integer id;
    private String userName;
    private String account;
    private String password;
    private Integer sex;
    private Integer age;
    private String phone;
    private String address;
    private Date createTime;
    private Date updateTime;
    private Integer status;
}
