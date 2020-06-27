package com.codingmc.modules.system.service.mapper;

import com.codingmc.modules.system.domain.Menu;
import com.codingmc.modules.system.service.dto.MenuDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-06T13:42:32+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu toEntity(MenuDto dto) {
        if ( dto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setId( dto.getId() );
        menu.setName( dto.getName() );
        menu.setSort( dto.getSort() );
        menu.setPath( dto.getPath() );
        menu.setComponent( dto.getComponent() );
        menu.setType( dto.getType() );
        menu.setPermission( dto.getPermission() );
        menu.setComponentName( dto.getComponentName() );
        menu.setIcon( dto.getIcon() );
        menu.setCache( dto.getCache() );
        menu.setHidden( dto.getHidden() );
        menu.setPid( dto.getPid() );
        menu.setIFrame( dto.getIFrame() );
        menu.setCreateTime( dto.getCreateTime() );

        return menu;
    }

    @Override
    public MenuDto toDto(Menu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setId( entity.getId() );
        menuDto.setName( entity.getName() );
        menuDto.setPath( entity.getPath() );
        menuDto.setType( entity.getType() );
        menuDto.setIcon( entity.getIcon() );
        menuDto.setPermission( entity.getPermission() );
        menuDto.setSort( entity.getSort() );
        menuDto.setComponent( entity.getComponent() );
        menuDto.setPid( entity.getPid() );
        menuDto.setIFrame( entity.getIFrame() );
        menuDto.setCache( entity.getCache() );
        menuDto.setHidden( entity.getHidden() );
        menuDto.setComponentName( entity.getComponentName() );
        menuDto.setCreateTime( entity.getCreateTime() );

        return menuDto;
    }

    @Override
    public List<Menu> toEntity(List<MenuDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Menu> list = new ArrayList<Menu>( dtoList.size() );
        for ( MenuDto menuDto : dtoList ) {
            list.add( toEntity( menuDto ) );
        }

        return list;
    }

    @Override
    public List<MenuDto> toDto(List<Menu> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MenuDto> list = new ArrayList<MenuDto>( entityList.size() );
        for ( Menu menu : entityList ) {
            list.add( toDto( menu ) );
        }

        return list;
    }
}
