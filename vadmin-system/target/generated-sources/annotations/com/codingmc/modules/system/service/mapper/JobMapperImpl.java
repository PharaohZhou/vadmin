package com.codingmc.modules.system.service.mapper;

import com.codingmc.modules.system.domain.Job;
import com.codingmc.modules.system.service.dto.JobDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-08T17:23:32+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Job toEntity(JobDto dto) {
        if ( dto == null ) {
            return null;
        }

        Job job = new Job();

        job.setId( dto.getId() );
        job.setName( dto.getName() );
        job.setSort( dto.getSort() );
        job.setEnabled( dto.getEnabled() );
        job.setDept( deptMapper.toEntity( dto.getDept() ) );
        job.setCreateTime( dto.getCreateTime() );

        return job;
    }

    @Override
    public JobDto toDto(Job entity) {
        if ( entity == null ) {
            return null;
        }

        JobDto jobDto = new JobDto();

        jobDto.setId( entity.getId() );
        jobDto.setName( entity.getName() );
        jobDto.setSort( entity.getSort() );
        jobDto.setEnabled( entity.getEnabled() );
        jobDto.setDept( deptMapper.toDto( entity.getDept() ) );
        jobDto.setCreateTime( entity.getCreateTime() );

        return jobDto;
    }

    @Override
    public List<Job> toEntity(List<JobDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Job> list = new ArrayList<Job>( dtoList.size() );
        for ( JobDto jobDto : dtoList ) {
            list.add( toEntity( jobDto ) );
        }

        return list;
    }

    @Override
    public List<JobDto> toDto(List<Job> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<JobDto> list = new ArrayList<JobDto>( entityList.size() );
        for ( Job job : entityList ) {
            list.add( toDto( job ) );
        }

        return list;
    }

    @Override
    public JobDto toDto(Job job, String deptSuperiorName) {
        if ( job == null && deptSuperiorName == null ) {
            return null;
        }

        JobDto jobDto = new JobDto();

        if ( job != null ) {
            jobDto.setId( job.getId() );
            jobDto.setName( job.getName() );
            jobDto.setSort( job.getSort() );
            jobDto.setEnabled( job.getEnabled() );
            jobDto.setDept( deptMapper.toDto( job.getDept() ) );
            jobDto.setCreateTime( job.getCreateTime() );
        }
        if ( deptSuperiorName != null ) {
            jobDto.setDeptSuperiorName( deptSuperiorName );
        }

        return jobDto;
    }
}
