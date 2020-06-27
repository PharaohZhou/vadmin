package com.codingmc.modules.system.repository;

import com.codingmc.modules.system.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName JobRepository
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/8
 * @Version V1.0
 **/
public interface JobRepository extends JpaRepository<Job,Long>, JpaSpecificationExecutor<Job> {
}
