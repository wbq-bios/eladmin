package me.zhengjie.repository;

import me.zhengjie.domain.BannerMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author wbq
* @date 2019-04-10
*/
public interface BannerMenuRepository extends JpaRepository<BannerMenu, Long>, JpaSpecificationExecutor {
}