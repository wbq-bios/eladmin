package me.zhengjie.service.dto;

import lombok.Data;
import me.zhengjie.domain.Picture;

import java.io.Serializable;
import java.util.List;

/**
* @author wbq
* @date 2019-04-16
*/
@Data
public class TravelPointDTO implements Serializable {

    private Long id;

    /**
     * 景点名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 图片url
     */
    private String imgUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 交通方式
     */
    private String transportation;

    /**
     * 英文名字
     */
    private String enName;

    /**
     * 图片list
     */
    private List<Picture> images;
}