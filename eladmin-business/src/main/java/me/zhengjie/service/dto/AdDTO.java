package me.zhengjie.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author wbq
* @date 2019-04-10
*/
@Data
public class AdDTO implements Serializable {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介内容
     */
    private String content;

    /**
     * 链接
     */
    private String url;

    /**
     * 图片链接url
     */
    private String imgUrl;
}