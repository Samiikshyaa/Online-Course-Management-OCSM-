package com.infodevelopers.ocsm.mapper;

import com.infodevelopers.ocsm.dto.assignmentDto.AssignmentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AssignmentMapper {

    @Select("select * from course c join assignment a on c.id = a.course_id where c.id = #{id}")
    AssignmentDto findById(Integer id);
}
