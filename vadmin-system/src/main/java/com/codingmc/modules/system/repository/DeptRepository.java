package com.codingmc.modules.system.repository;

import com.codingmc.modules.system.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @ClassName DeptRepository
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/29
 * @Version V1.0
 **/
public interface DeptRepository extends JpaRepository<Dept,Long>, JpaSpecificationExecutor<Dept> {

    /**
     * 根据角色ID查询
     * @param id
     * @return
     */
    Set<Dept> findByRoles_Id(Long id);

    /**
     * 根据id查询子部门
     * @param id
     * @return
     */
    List<Dept> findByPid(Long id);

    /**
     * 根据id查询部门名
     * @param pid
     * @return
     */
    @Query(value = "select name from dept where id=?1", nativeQuery = true)
    String findNameById(Long id);
}
