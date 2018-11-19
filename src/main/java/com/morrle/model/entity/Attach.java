package com.morrle.model.entity;

import lombok.Data;

/**
 * @author morrle
 * @date 2018/10/30 10:54
 * 附件
 **/
@Data
public class Attach {


    private String id;
    /**
     * 附件文件名
     */
    private String name;

    /**
     * 附件类型
     */
    private String type;

    /**
     * 文件存放路径
     */
    private String path;

    /**
     * 上传时间
     */
    private Long created;

    /**
     * 上传者
     */
    private String authorId;

    /**
     * 状态 启用：Y，禁用：N
     */
    private String status;

}
