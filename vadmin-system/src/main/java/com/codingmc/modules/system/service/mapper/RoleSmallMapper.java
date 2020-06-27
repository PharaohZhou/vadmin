package com.codingmc.modules.system.service.mapper;

import com.codingmc.base.BaseMapper;
import com.codingmc.modules.system.domain.Role;
import com.codingmc.modules.system.service.dto.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @ClassName RoleSmallMapper
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/29
 * @Version V1.0
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDto, Role> {
}
