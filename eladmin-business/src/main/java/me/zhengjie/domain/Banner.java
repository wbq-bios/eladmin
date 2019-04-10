package me.zhengjie.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author wbq
* @date 2019-04-10
*/
@Entity
@Data
@Table(name="banner")
public class Banner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 图片地址
     */
    @Column(name = "img_address",nullable = false)
    private String imgAddress;

    /**
     * 排序
     */
    @Column(name = "sort_num",nullable = false)
    private Integer sortNum;

    /**
     * 图片描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 是否在使用
     */
    @Column(name = "isuse",nullable = false)
    private Integer isuse;

    /**
     * 图片别名
     */
    @Column(name = "name",nullable = false)
    private String name;
}