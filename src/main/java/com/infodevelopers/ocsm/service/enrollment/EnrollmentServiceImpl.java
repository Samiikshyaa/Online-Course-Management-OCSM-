package com.infodevelopers.ocsm.service.enrollment;

import com.infodevelopers.ocsm.dto.enrollmentDto.EnrollmentDto;
import com.infodevelopers.ocsm.entity.User;
import com.infodevelopers.ocsm.repository.CourseRepository;
import com.infodevelopers.ocsm.repository.UserRepository;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService{

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public EnrollmentDto create(EnrollmentDto enrollmentDto) {
        // from enrollmentDto find user by the username of user.
        User user = userRepository.findStudentByUserName(enrollmentDto.getUserName(), enrollmentDto.getRole());


        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<EnrollmentDto> findAll() {
        return null;
    }

    @Override
    public EnrollmentDto update(EnrollmentDto enrollmentDto) {
        return null;
    }
}
