package me.zhengjie.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @description:
 * @author: wbq
 * @create: 2019-04-15 16:29
 **/
@Entity
@Data
@Table(name="travel_picture")
public class TravelPoint2Images implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 景点名称
     */
    @Column(name = "travel_point_id")
    private Long travelId;
    /**
     * 图片id
     */
    @Column(name = "picture_id")
    private Long pictureId;
}
