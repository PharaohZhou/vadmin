package com.codingmc.modules.system.service.mapper;

import com.codingmc.base.BaseMapper;
import com.codingmc.modules.system.domain.Menu;
import com.codingmc.modules.system.service.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @ClassName MenuMapper
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/6
 * @Version V1.0
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDto, Menu> {
}
