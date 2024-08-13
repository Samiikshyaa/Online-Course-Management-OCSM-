package com.infodevelopers.ocsm.service.course;

import com.infodevelopers.ocsm.dto.courseDto.CourseDto;
import com.infodevelopers.ocsm.entity.Course;
import com.infodevelopers.ocsm.entity.User;
import com.infodevelopers.ocsm.repository.CourseRepository;
import com.infodevelopers.ocsm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseDto> findAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();

//       Inside entity:  courseName, description, schedule, List<User> instructors

        for (Course data:courses) {
            List<String> instructorsName = data.getInstructors().stream().map(user -> user.getUserName()).collect(Collectors.toList());
            CourseDto c = CourseDto.builder()
                    .courseName(data.getCourseName())
                    .description(data.getDescription())
                    .schedule(data.getSchedule())
                    .instructors(instructorsName)
                    .build();
            courseDtos.add(c);
        }
        return courseDtos;
    }

    @Override
    public CourseDto update(CourseDto courseDto) {

        //change to the entity.
        Optional<Course> optionalCourse = courseRepository.findById(courseDto.getId());
        Course existingCourse = optionalCourse.get();
        existingCourse.setId(courseDto.getId());
        existingCourse.setCourseName(courseDto.getCourseName());
        existingCourse.setDescription(courseDto.getDescription());
        existingCourse.setSchedule(courseDto.getSchedule());
//        existingCourse.setInstructors(courseDto.getInstructors());
//        needed in userType but we have String type LIST.

        List<User> updatedInstructors = userRepository.findByAllUserName(courseDto.getInstructors());
        existingCourse.setInstructors(updatedInstructors);

        Course course = courseRepository.save(existingCourse);

        List<String> instructorsName = course.getInstructors().stream().map(user -> user.getUserName()).collect(Collectors.toList());
        CourseDto c = CourseDto.builder()
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .schedule(course.getSchedule())
                .instructors(instructorsName)
                .build();
        return c;
    }

    @Override
    public boolean findById(Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()){
            return false;
        }else {
            return true;
        }
    }
}
