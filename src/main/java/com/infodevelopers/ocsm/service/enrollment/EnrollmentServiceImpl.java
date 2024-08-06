package com.infodevelopers.ocsm.service.enrollment;

import com.infodevelopers.ocsm.dto.enrollmentDto.EnrollmentDto;
import com.infodevelopers.ocsm.entity.Course;
import com.infodevelopers.ocsm.entity.Enrollment;
import com.infodevelopers.ocsm.entity.User;
import com.infodevelopers.ocsm.repository.CourseRepository;
import com.infodevelopers.ocsm.repository.EnrollmentRepository;
import com.infodevelopers.ocsm.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
        Optional<User> optionalUser = userRepository.findUserByMobileNumber(enrollmentDto.getMobileNumber());
        Course courseFound = courseRepository.findByCourseNameAndAndDescription(enrollmentDto.getCourse(), enrollmentDto.getDescription());

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            boolean isStudent = user.getRole().stream().anyMatch(role -> role.getRoleName().equalsIgnoreCase("student"));

            if (isStudent) {
                Enrollment enrollment = Enrollment.builder()
                        .id(enrollmentDto.getId())
                        .enrolledAt(enrollmentDto.getEnrolledAt())
                        .user(user)
                        .course(courseFound)
                        .build();
                enrollmentRepository.save(enrollment);
                EnrollmentDto dto = EnrollmentDto.builder()
                        .id(enrollment.getId())
                        .enrolledAt(enrollment.getEnrolledAt())
                        .mobileNumber(enrollment.getUser().getMobileNumber())
                        .course(enrollment.getCourse().getCourseName())
                        .description(enrollment.getCourse().getDescription())
                        .build();
                return dto;
            } else {
                log.error("{} is not student",user.getUserName());
                return null;
            }
        }
        return null;
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
                            .mobileNumber(data.getUser().getMobileNumber())
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

    @Override
    public List<EnrollmentDto> findByMobileNumber(String mobileNumber) {
        List<Enrollment> enrollmentList = enrollmentRepository.findByMobileNumber(mobileNumber);
        List<EnrollmentDto> dtoList = new ArrayList<>();
        enrollmentList.stream()
                .forEach(data -> {
                    dtoList.add(EnrollmentDto.builder()
                            .id(data.getId())
                            .enrolledAt(data.getEnrolledAt())
                            .mobileNumber(data.getUser().getUserName())
                            .course(data.getCourse().getCourseName())
                            .description(data.getCourse().getDescription())
                            .build());
                });
        return dtoList;
    }

    @Override
    public List<EnrollmentDto> findByUserName(String userName) {
        return null;
    }
}
