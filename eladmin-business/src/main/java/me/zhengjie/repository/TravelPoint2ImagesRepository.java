package me.zhengjie.repository;

import me.zhengjie.domain.TalentRecruitment;
import me.zhengjie.domain.TravelPoint2Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TravelPoint2ImagesRepository extends JpaRepository<TravelPoint2Images, Long>, JpaSpecificationExecutor {
    @Query(value = "select * from  travel_picture where travel_point_id=?1",nativeQuery = true)
    List<TravelPoint2Images> getImgs(long id);

    @Transactional
    @Modifying
    @Query(value = "delete from travel_picture where travel_point_id=?1",nativeQuery = true)
    void del(Long id);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "delete from travel_picture where id in(?1)",nativeQuery = true)
    void bashDel(List<Long> picIdList);
}
