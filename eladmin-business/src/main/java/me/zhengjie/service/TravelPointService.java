package me.zhengjie.service;

import me.zhengjie.domain.TravelPoint;
import me.zhengjie.service.dto.TravelPointDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author wbq
* @date 2019-04-10
*/
@CacheConfig(cacheNames = "travelPoint")
public interface TravelPointService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TravelPointDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TravelPointDTO create(TravelPoint resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(TravelPoint resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}