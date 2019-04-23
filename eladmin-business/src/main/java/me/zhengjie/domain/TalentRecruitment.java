package me.zhengjie.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author wbq
* @date 2019-04-15
*/
@Entity
@Data
@Table(name="talent_recruitment")
public class TalentRecruitment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 发布时间
     */
    @Column(name = "update_time",nullable = false)
    private Timestamp updateTime;

    /**
     * 职位名称
     */
    @Column(name = "position_name",nullable = false)
    private String positionName;

    /**
     * 地址
     */
    @Column(name = "address",nullable = false)
    private String address;

    /**
     * 职位类别
     */
    @Column(name = "categories",nullable = false)
    private String categories;

    /**
     * 内容
     */
    @Column(name = "content",nullable = false)
    private String content;
}