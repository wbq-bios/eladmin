package me.zhengjie.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.domain.TalentRecruitment;
import me.zhengjie.service.dto.TalentRecruitmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author wbq
* @date 2019-04-15
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TalentRecruitmentMapper extends EntityMapper<TalentRecruitmentDTO, TalentRecruitment> {

}