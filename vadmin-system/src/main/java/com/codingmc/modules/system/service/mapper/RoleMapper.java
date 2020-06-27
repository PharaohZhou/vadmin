package com.codingmc.modules.system.service.mapper;

import com.codingmc.base.BaseMapper;
import com.codingmc.modules.system.domain.Role;
import com.codingmc.modules.system.service.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @ClassName RoleMapper
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
@Mapper(componentModel = "spring",uses = {DeptMapper.class,MenuMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {
}
