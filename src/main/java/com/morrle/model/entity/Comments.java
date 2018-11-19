package com.morrle.model.entity;

import lombok.Data;

/**
 * @author morrle
 * @date 2018/10/30 10:57
 * 评论
 **/
@Data
public class Comments {


    private String id;
    /**
     * 文章ID
     */
    private String cId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Long created;

    /**
     * 评论者姓名
     */
    private String author;

    /**
     * 评论者email
     */
    private String email;

    /**
     * 评论者客户端
     */
    private String agent;

    /**
     * 评论审核状态： Y/N
     */
    private String status;


    /**
     * 父级评论ID
     */
    private String parent;

}
