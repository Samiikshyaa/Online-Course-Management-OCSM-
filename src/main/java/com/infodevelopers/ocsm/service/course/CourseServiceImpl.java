package com.infodevelopers.ocsm.service.course;

import com.infodevelopers.ocsm.dto.courseDto.CourseDto;
import com.infodevelopers.ocsm.entity.Course;
import com.infodevelopers.ocsm.entity.User;
import com.infodevelopers.ocsm.repository.CourseRepository;
import com.infodevelopers.ocsm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

//  create course
    @Override
    public CourseDto create(CourseDto courseDto) {

        //mapping the instructor with the User entity
        List<User> users = userRepository.findByAllUserName(courseDto.getInstructors());

        Course course = Course.builder()
                .courseName(courseDto.getCourseName())
                .description(courseDto.getDescription())
                .schedule(courseDto.getSchedule())
                .instructors(users)
                .build();

        courseRepository.save(course);

        return courseDto;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<CourseDto> findAll() {
        return null;
    }

    @Override
    public CourseDto update(CourseDto courseDto) {
        return null;
    }
}
