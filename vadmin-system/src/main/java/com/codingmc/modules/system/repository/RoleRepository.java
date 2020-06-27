package com.codingmc.modules.system.repository;

import com.codingmc.modules.system.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

/**
 * @ClassName RoleRepository
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/12
 * @Version V1.0
 **/
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    /**
     * 根据用户 id 查询 角色
     * 这是使用Spring数据JPA的性能表现特征。签名Users_Id将被转换为JPQL x.users.id
     * @param id
     * @return
     */
    Set<Role> findByUsers_Id(Long id);

    /**
     * 根据角色名查询
     * @param name
     * @return
     */
    Role findByName(String name);
}
