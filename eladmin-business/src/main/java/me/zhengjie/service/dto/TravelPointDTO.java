package me.zhengjie.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author wbq
* @date 2019-04-10
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
}