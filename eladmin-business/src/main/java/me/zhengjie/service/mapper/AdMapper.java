package me.zhengjie.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.domain.Ad;
import me.zhengjie.service.dto.AdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author wbq
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper extends EntityMapper<AdDTO, Ad> {

}