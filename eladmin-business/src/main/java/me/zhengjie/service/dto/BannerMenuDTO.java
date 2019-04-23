package me.zhengjie.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author wbq
* @date 2019-04-10
*/
@Data
public class BannerMenuDTO implements Serializable {

    private Long id;

    /**
     * 标题
     */
    private String titile;

    /**
     * 简介描述
     */
    private String description;

    /**
     * 对应图标url
     */
    private String imgUrl;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}