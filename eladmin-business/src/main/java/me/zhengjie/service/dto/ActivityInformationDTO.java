package me.zhengjie.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author wbq
* @date 2019-04-10
*/
@Data
public class ActivityInformationDTO implements Serializable {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章链接
     */
    private String url;

    /**
     * 所属分类，1：热门活动，2，2景区新闻，3：旅游新闻
     */
    private Long category;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    /**
     * 更新日期
     */
    private Timestamp updateTime;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 在首页上的简介
     */
    private String description;
}