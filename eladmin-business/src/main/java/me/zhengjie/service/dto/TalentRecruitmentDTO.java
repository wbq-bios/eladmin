package me.zhengjie.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author wbq
* @date 2019-04-15
*/
@Data
public class TalentRecruitmentDTO implements Serializable {

    private Long id;

    /**
     * 发布时间
     */
    private Timestamp updateTime;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 地址
     */
    private String address;

    /**
     * 职位类别
     */
    private String categories;

    /**
     * 内容
     */
    private String content;
}