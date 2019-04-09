package me.zhengjie.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.domain.Banner;
import me.zhengjie.service.dto.BannerDTO;
import me.zhengjie.repository.BannerRepository;
import me.zhengjie.service.mapper.BannerMapper;
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
@CacheConfig(cacheNames = "banner")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BannerQueryService {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BannerDTO banner, Pageable pageable){
        Page<Banner> page = bannerRepository.findAll(new Spec(banner),pageable);
        return PageUtil.toPage(page.map(bannerMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BannerDTO banner){
        return bannerMapper.toDto(bannerRepository.findAll(new Spec(banner)));
    }

    class Spec implements Specification<Banner> {

        private BannerDTO banner;

        public Spec(BannerDTO banner){
            this.banner = banner;
        }

        @Override
        public Predicate toPredicate(Root<Banner> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
//
//    @Cacheable(keyGenerator = "keyGenerator")
//    public List<BannerDTO> getShowBanner(){
//        List<BannerDTO> res=new ArrayList<>();
//        res=bannerRepository
//    }
}