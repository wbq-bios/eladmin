package me.zhengjie.repository;

import me.zhengjie.domain.Banner;
import me.zhengjie.service.dto.BannerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author wbq
* @date 2019-04-09
*/
public interface BannerRepository extends JpaRepository<Banner, Long>, JpaSpecificationExecutor {
//    @Query(value = "select * from banner where isuse=1")
//    List<BannerDTO> getShowBanner();

}