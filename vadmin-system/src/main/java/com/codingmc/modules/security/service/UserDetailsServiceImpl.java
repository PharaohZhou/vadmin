package com.codingmc.modules.security.service;

import com.codingmc.exception.BadRequestException;
import com.codingmc.modules.security.service.dto.JwtUserDto;
import com.codingmc.modules.system.service.RoleService;
import com.codingmc.modules.system.service.UserService;
import com.codingmc.modules.system.service.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
@Service("userDetailsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;

    public UserDetailsServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public JwtUserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.findByName(username);
        if (userDto == null) {
            throw new BadRequestException("账号不存在");
        } else {
            if (!userDto.getEnabled()) {
                throw new BadRequestException("账号未激活");
            }
            return new JwtUserDto(
                    userDto,
                    roleService.mapToGrantedAuthorities(userDto)
            );
        }
    }
}
