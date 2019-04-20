package me.zhengjie.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.domain.TalentRecruitment;
import me.zhengjie.service.dto.TalentRecruitmentDTO;
import me.zhengjie.repository.TalentRecruitmentRepository;
import me.zhengjie.service.mapper.TalentRecruitmentMapper;
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
@CacheConfig(cacheNames = "talentRecruitment")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TalentRecruitmentQueryService {

    @Autowired
    private TalentRecruitmentRepository talentRecruitmentRepository;

    @Autowired
    private TalentRecruitmentMapper talentRecruitmentMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TalentRecruitmentDTO talentRecruitment, Pageable pageable){
        Page<TalentRecruitment> page = talentRecruitmentRepository.findAll(new Spec(talentRecruitment),pageable);
        return PageUtil.toPage(page.map(talentRecruitmentMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TalentRecruitmentDTO talentRecruitment){
        return talentRecruitmentMapper.toDto(talentRecruitmentRepository.findAll(new Spec(talentRecruitment)));
    }

    class Spec implements Specification<TalentRecruitment> {

        private TalentRecruitmentDTO talentRecruitment;

        public Spec(TalentRecruitmentDTO talentRecruitment){
            this.talentRecruitment = talentRecruitment;
        }

        @Override
        public Predicate toPredicate(Root<TalentRecruitment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(talentRecruitment.getId())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("id").as(Long.class),talentRecruitment.getId()));
                }
                if(!ObjectUtils.isEmpty(talentRecruitment.getPositionName())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("position_name").as(String.class),"%"+talentRecruitment.getPositionName()+"%"));
                }
                if(!ObjectUtils.isEmpty(talentRecruitment.getCategories())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("categories").as(String.class),"%"+talentRecruitment.getCategories()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}