package com.infodevelopers.ocsm.service.enrollment;

import com.infodevelopers.ocsm.dto.enrollmentDto.EnrollmentDto;
import com.infodevelopers.ocsm.entity.Course;
import com.infodevelopers.ocsm.entity.Enrollment;
import com.infodevelopers.ocsm.entity.User;
import com.infodevelopers.ocsm.repository.CourseRepository;
import com.infodevelopers.ocsm.repository.EnrollmentRepository;
import com.infodevelopers.ocsm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(UserRepository userRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public EnrollmentDto create(EnrollmentDto enrollmentDto) {
        // from enrollmentDto find user by the username of user.
        User user = userRepository.findStudentByUserName(enrollmentDto.getUserName(), enrollmentDto.getRole());
        Course course = courseRepository.findByCourseNameAndAndDescription(enrollmentDto.getCourse(), enrollmentDto.getDescription());
        Enrollment enrollment = null;
        if (user != null) {
            enrollment = Enrollment.builder()
                    .id(enrollmentDto.getId())
                    .enrolledAt(enrollmentDto.getEnrolledAt())
                    .user(user)
                    .course(course)
                    .build();
        }
        enrollmentRepository.save(enrollment);

        EnrollmentDto dto = EnrollmentDto.builder()
                .id(enrollment.getId())
                .enrolledAt(enrollment.getEnrolledAt())
                .userName(enrollment.getUser().getUserName())
                .course(enrollment.getCourse().getCourseName())
                .description(enrollment.getCourse().getDescription())
                .build();
        return dto;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<EnrollmentDto> findAll() {
        List<Enrollment> enrollmentList = enrollmentRepository.findAll();
        List<EnrollmentDto> dtoList = new ArrayList<>();
                enrollmentList.stream()
                .forEach(data -> {
                  dtoList.add(EnrollmentDto.builder()
                          .id(data.getId())
                          .enrolledAt(data.getEnrolledAt())
                          .userName(data.getUser().getUserName())
                          .course(data.getCourse().getCourseName())
                          .description(data.getCourse().getDescription())
                          .build());
                });
        return dtoList;
    }

    @Override
    public EnrollmentDto update(EnrollmentDto enrollmentDto) {
        return null;
    }
}
