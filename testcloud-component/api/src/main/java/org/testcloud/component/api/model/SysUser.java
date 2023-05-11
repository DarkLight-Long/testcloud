package org.testcloud.component.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 作为远程对象，需要支持序列化
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer id;
    private String userName;
    private String sex;
    private Integer age;
    private String email;
    private Integer phone;
    private String idCard;

}
