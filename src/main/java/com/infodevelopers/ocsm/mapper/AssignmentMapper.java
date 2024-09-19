package com.infodevelopers.ocsm.mapper;

import com.infodevelopers.ocsm.dto.assignmentDto.AssignmentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssignmentMapper {

    @Select("select a.* from tbl_course c join tbl_assignment a on c.id = a.course_id where a.course_id = #{id}")
    List<AssignmentDto> findById(Integer id);
}
