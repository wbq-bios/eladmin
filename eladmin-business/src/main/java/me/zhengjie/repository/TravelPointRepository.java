package me.zhengjie.repository;

import me.zhengjie.domain.TravelPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author wbq
* @date 2019-04-16
*/
public interface TravelPointRepository extends JpaRepository<TravelPoint, Long>, JpaSpecificationExecutor {
    @Query(value = "select * from travel_point",nativeQuery = true)
    List<TravelPoint> showTravelPoint();

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "delete from travel_point where id in(?1)",nativeQuery = true)
    void bashDel(List<Long> idList);
}