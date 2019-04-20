package me.zhengjie.service;

import me.zhengjie.domain.TalentRecruitment;
import me.zhengjie.service.dto.TalentRecruitmentDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @author wbq
* @date 2019-04-15
*/
@CacheConfig(cacheNames = "talentRecruitment")
public interface TalentRecruitmentService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TalentRecruitmentDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TalentRecruitmentDTO create(TalentRecruitment resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(TalentRecruitment resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
    /**
     *
     * @return
     */
    @CacheEvict(allEntries = true)
    List<TalentRecruitment> getTalentRecruitment();
}