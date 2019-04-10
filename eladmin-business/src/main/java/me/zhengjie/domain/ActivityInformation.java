package me.zhengjie.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author wbq
* @date 2019-04-10
*/
@Entity
@Data
@Table(name="activity_information")
public class ActivityInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 标题
     */
    @Column(name = "title",nullable = false)
    private String title;

    /**
     * 文章链接
     */
    @Column(name = "url",nullable = false)
    private String url;

    /**
     * 所属分类1：热门活动，2：景区新闻3：旅游新闻
     */
    @Column(name = "category",nullable = false)
    private Long category;

    /**
     * 文章作者
     */
    @Column(name = "author",nullable = false)
    private String author;

    /**
     * 创建日期
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 更新日期
     */
    @Column(name = "update_time",nullable = false)
    private Timestamp updateTime;

    /**
     * 文章内容
     */
    @Column(name = "content",nullable = false)
    private String content;

    /**
     * 在首页上的简介
     */
    @Column(name = "description",nullable = false)
    private String description;

    /**
     * 主页上显示
     */
    @Column(name = "is_show",nullable = false)
    private Integer isShow;
}