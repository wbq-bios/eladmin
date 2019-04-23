package me.zhengjie.repository;

import me.zhengjie.domain.TalentRecruitment;
import me.zhengjie.domain.TravelPoint2Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelPoint2ImagesRepository extends JpaRepository<TravelPoint2Images, Long>, JpaSpecificationExecutor {
    @Query(value = "select * from  travel_picture where travel_point_id=?1",nativeQuery = true)
    List<TravelPoint2Images> getImgs(long id);
}
