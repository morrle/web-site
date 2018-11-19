package com.morrle.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author morrle
 * @date 2018/11/04 14:22
 * 文章
 **/
@Data
public class Articles {


    private String id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章markdown内容
     */
    private String markdown;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 修改时间
     */
    private Long modified;

    /**
     * 作者ID
     */
    private String authorId;

    /**
     * 文章点击次数
     */
    private Integer hits;

    /**
     * 文章类型： PAGE/POST
     */
    private String type;

    /**
     * 文章标签
     */
    private String tags;

    /**
     * 文章分类
     */
    private String categories;

    /**
     * 文章状态：N:草稿,Y：发布
     */
    private String status;

    /**
     * 是否允许评论
     */
    private Boolean allowComment;

    /**
     * 文章评论次数
     */
    private Integer commentsNum;

}
