package me.zhengjie.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.domain.TravelPoint;
import me.zhengjie.service.dto.TravelPointDTO;
import me.zhengjie.repository.TravelPointRepository;
import me.zhengjie.service.mapper.TravelPointMapper;
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
@CacheConfig(cacheNames = "travelPoint")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TravelPointQueryService {

    @Autowired
    private TravelPointRepository travelPointRepository;

    @Autowired
    private TravelPointMapper travelPointMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TravelPointDTO travelPoint, Pageable pageable){
        Page<TravelPoint> page = travelPointRepository.findAll(new Spec(travelPoint),pageable);
        return PageUtil.toPage(page.map(travelPointMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TravelPointDTO travelPoint){
        return travelPointMapper.toDto(travelPointRepository.findAll(new Spec(travelPoint)));
    }

    class Spec implements Specification<TravelPoint> {

        private TravelPointDTO travelPoint;

        public Spec(TravelPointDTO travelPoint){
            this.travelPoint = travelPoint;
        }

        @Override
        public Predicate toPredicate(Root<TravelPoint> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(travelPoint.getId())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("id").as(Long.class),travelPoint.getId()));
                }
                if(!ObjectUtils.isEmpty(travelPoint.getName())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("name").as(String.class),travelPoint.getName()));
                }
                if(!ObjectUtils.isEmpty(travelPoint.getEnName())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("en_name").as(String.class),"%"+travelPoint.getEnName()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}