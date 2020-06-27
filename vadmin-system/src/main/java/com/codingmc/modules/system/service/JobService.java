package com.codingmc.modules.system.service;

import com.codingmc.modules.system.service.dto.JobQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @ClassName JobService
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
public interface JobService {

    /**
     * 分页查询
     * @param criteria
     * @param pageable
     * @return
     */
    Map<String, Object> queryAll(JobQueryCriteria criteria, Pageable pageable);
}
