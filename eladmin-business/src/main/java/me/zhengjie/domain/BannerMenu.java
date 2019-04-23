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
@Table(name="banner_menu")
public class BannerMenu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 标题
     */
    @Column(name = "titile",nullable = false)
    private String titile;

    /**
     * 简介描述
     */
    @Column(name = "description",nullable = false)
    private String description;

    /**
     * 对应图标url
     */
    @Column(name = "img_url",nullable = false)
    private String imgUrl;

    /**
     * 排序
     */
    @Column(name = "sort_num",nullable = false)
    private Integer sortNum;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}