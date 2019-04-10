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
@Table(name="travel_point")
public class TravelPoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 景点名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 排序
     */
    @Column(name = "sort_num")
    private Integer sortNum;

    /**
     * 图片url
     */
    @Column(name = "img_url",nullable = false)
    private String imgUrl;

    /**
     * 描述
     */
    @Column(name = "description",nullable = false)
    private String description;
}