package me.zhengjie.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.domain.ActivityInformation;
import me.zhengjie.service.dto.ActivityInformationDTO;
import me.zhengjie.repository.ActivityInformationRepository;
import me.zhengjie.service.mapper.ActivityInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "activityInformation")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ActivityInformationQueryService {

    @Autowired
    private ActivityInformationRepository activityInformationRepository;

    @Autowired
    private ActivityInformationMapper activityInformationMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ActivityInformationDTO activityInformation, Pageable pageable){
        Page<ActivityInformation> page = activityInformationRepository.findAll(new Spec(activityInformation),pageable);
        return PageUtil.toPage(page.map(activityInformationMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ActivityInformationDTO activityInformation){
        return activityInformationMapper.toDto(activityInformationRepository.findAll(new Spec(activityInformation)));
    }

    class Spec implements Specification<ActivityInformation> {

        private ActivityInformationDTO activityInformation;

        public Spec(ActivityInformationDTO activityInformation){
            this.activityInformation = activityInformation;
        }

        @Override
        public Predicate toPredicate(Root<ActivityInformation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(activityInformation.getId())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("id").as(Long.class),activityInformation.getId()));
                }
                if(!ObjectUtils.isEmpty(activityInformation.getTitle())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+activityInformation.getTitle()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}