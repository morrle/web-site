package com.morrle.model.entity;

import lombok.Data;
/**
 * @author morrle
 * @date 2018/10/30 12：00
 * 用户
 **/
@Data
public class Users {

    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * email
     */
    private String email;

    /**
     * 创建日期
     */
    private Long created;

    /**
     * 最后登陆时间
     */
    private Long activated;


}
