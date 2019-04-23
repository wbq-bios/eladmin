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
@Table(name="ad")
public class Ad implements Serializable {

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
     * 简介内容
     */
    @Column(name = "content",nullable = false)
    private String content;

    /**
     * 链接
     */
    @Column(name = "url",nullable = false)
    private String url;

    /**
     * 图片链接url
     */
    @Column(name = "img_url",nullable = false)
    private String imgUrl;
}