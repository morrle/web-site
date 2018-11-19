package com.morrle.model.entity;

import lombok.Data;

/**
 * @author morrle
 * @date 2018/11/05 10:04
 * 配置项
 **/
@Data
public class Options {

    private String id;

    /**
     * 配置项键
     */
    private String key;

    /**
     * 配置项值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

}
