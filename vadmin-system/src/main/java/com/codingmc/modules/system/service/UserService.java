package com.codingmc.modules.system.service;

import com.codingmc.modules.system.domain.User;
import com.codingmc.modules.system.service.dto.UserDto;
import com.codingmc.modules.system.service.dto.UserQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
public interface UserService {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    UserDto findByName(String username);

    /**
     * 查询全部
     * @param criteria
     * @param pageable
     * @return
     */
    Object queryAll(UserQueryCriteria criteria, Pageable pageable);

    /**
     * 插入一个用户
     * @param resources
     * @return
     */
    UserDto create(User resources);

    /**
     * 编辑用户
     * @param resources
     */
    void update(User resources);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    UserDto findById(Long id);

    /**
     * 删除用户
     * @param ids
     */
    void delete(Set<Long> ids);
}
