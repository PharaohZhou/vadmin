package com.codingmc.modules.system.service.impl;

import com.codingmc.modules.system.domain.Job;
import com.codingmc.modules.system.repository.DeptRepository;
import com.codingmc.modules.system.repository.JobRepository;
import com.codingmc.modules.system.service.DeptService;
import com.codingmc.modules.system.service.JobService;
import com.codingmc.modules.system.service.dto.JobDto;
import com.codingmc.modules.system.service.dto.JobQueryCriteria;
import com.codingmc.modules.system.service.mapper.JobMapper;
import com.codingmc.utils.PageUtil;
import com.codingmc.utils.QueryHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JobServiceImpl
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/8
 * @Version V1.0
 **/
@Service
@CacheConfig(cacheNames = "job")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private DeptRepository deptRepository;
    @Override
    @Cacheable
    public Map<String, Object> queryAll(JobQueryCriteria criteria, Pageable pageable) {
        Page<Job> page = jobRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder),pageable);
        List<JobDto> jobs = new ArrayList<>();
        for (Job job : page.getContent()) {
            jobs.add(jobMapper.toDto(job, deptRepository.findNameById(job.getDept().getPid())));
        }
        return PageUtil.toPage(jobs, page.getTotalElements());
    }
}
