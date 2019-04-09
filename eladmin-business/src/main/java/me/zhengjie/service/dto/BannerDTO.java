package me.zhengjie.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author wbq
* @date 2019-04-09
*/
@Data
public class BannerDTO implements Serializable {

    private Long id;

    /**
     * 图片地址
     */
    private String imgAddress;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 图片描述
     */
    private String description;

    /**
     * 是否在使用
     */
    private Integer isuse;

    /**
     * 图片别名
     */
    private String name;
}