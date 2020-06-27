package com.codingmc.modules.system.repository;

import com.codingmc.modules.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName UserRepository
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {


    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUsername(String username);
}
