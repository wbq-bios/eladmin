package me.zhengjie.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.domain.ActivityInformation;
import me.zhengjie.service.dto.ActivityInformationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author wbq
* @date 2019-04-26
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActivityInformationMapper extends EntityMapper<ActivityInformationDTO, ActivityInformation> {

}