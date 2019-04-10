package me.zhengjie.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.domain.Ad;
import me.zhengjie.service.dto.AdDTO;
import me.zhengjie.repository.AdRepository;
import me.zhengjie.service.mapper.AdMapper;
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
@CacheConfig(cacheNames = "ad")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AdQueryService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdMapper adMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AdDTO ad, Pageable pageable){
        Page<Ad> page = adRepository.findAll(new Spec(ad),pageable);
        return PageUtil.toPage(page.map(adMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AdDTO ad){
        return adMapper.toDto(adRepository.findAll(new Spec(ad)));
    }

    class Spec implements Specification<Ad> {

        private AdDTO ad;

        public Spec(AdDTO ad){
            this.ad = ad;
        }

        @Override
        public Predicate toPredicate(Root<Ad> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(ad.getId())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("id").as(Long.class),ad.getId()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}