package me.zhengjie.service;

import me.zhengjie.domain.BannerMenu;
import me.zhengjie.service.dto.BannerMenuDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author wbq
* @date 2019-04-10
*/
@CacheConfig(cacheNames = "bannerMenu")
public interface BannerMenuService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    BannerMenuDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    BannerMenuDTO create(BannerMenu resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(BannerMenu resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}