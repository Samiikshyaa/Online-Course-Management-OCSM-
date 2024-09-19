package com.infodevelopers.ocsm.mapper;

import com.infodevelopers.ocsm.dto.assignmentDto.AssignmentDto;
import com.infodevelopers.ocsm.dto.assignmentDto.AssignmentResponseDto;
import com.infodevelopers.ocsm.entity.Assignment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssignmentMapper {

    @Select("select " +
            "a.id as id, " +
            "a.title as title, " +
            "a.description as description, " +
            "a.due_date as dueDate, " +
            "a.assignment_path as filePath, " +
            "c.course_name as courseName  " +
            "from tbl_course c " +
            "join tbl_assignment a " +
            "on c.id = a.course_id " +
            "where a.course_id = #{id}")
    List<Assignment> findById(Integer id);
}
