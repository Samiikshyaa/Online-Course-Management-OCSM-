package com.infodevelopers.ocsm.service.assignment;

import com.infodevelopers.ocsm.dto.assignmentDto.AssignmentDto;
import com.infodevelopers.ocsm.entity.Assignment;
import com.infodevelopers.ocsm.entity.Course;
import com.infodevelopers.ocsm.repository.AssignmentRepository;
import com.infodevelopers.ocsm.repository.CourseRepository;
import com.infodevelopers.ocsm.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {

    private final CourseRepository courseRepository;
    private final AssignmentRepository assignmentRepository;

    private final FileStorageService fileStorageService;

    public AssignmentServiceImpl(CourseRepository courseRepository, AssignmentRepository assignmentRepository, FileStorageService fileStorageService) {
        this.courseRepository = courseRepository;
        this.assignmentRepository = assignmentRepository;
        this.fileStorageService = fileStorageService;
    }


//    @Override
//    public AssignmentDto createAssignment(AssignmentDto assignmentDto) {
//        Optional<Course> optionalCourse = courseRepository.findById(assignmentDto.getCourseId());
//        if (optionalCourse.isPresent()) {
//            Course course = optionalCourse.get();
//
//            String filePath = fileStorageService.storeFile(file);
//
//            Assignment assignment = Assignment.builder()
//                    .title(assignmentDto.getTitle())
//                    .description(assignmentDto.getDescription())
//                    .dueDate(assignmentDto.getDueDate())
//                    .assignentPath(filePath)
//                    .course(course)
//                    .build();
//
//            assignmentRepository.save(assignment);
//
//            return AssignmentDto.builder()
//                    .id(assignment.getId())
//                    .title(assignment.getTitle())
//                    .description(assignment.getDescription())
//                    .dueDate(assignment.getDueDate()).filePath(filePath)
//                    .courseId(course.getId())
//                    .build();
//
//        } else {
//            log.error("Course{} not found", assignmentDto.getCourseId());
//            return null;
//        }
//
//
//    }

    @Override
    public AssignmentDto create(AssignmentDto assignmentDto) {
        Optional<Course> optionalCourse = courseRepository.findById(assignmentDto.getCourseId());
        String filePath = fileStorageService.storeFile(assignmentDto.getFile());
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            Assignment assignment = Assignment.builder()
                    .title(assignmentDto.getTitle())
                    .description(assignmentDto.getDescription())
                    .dueDate(assignmentDto.getDueDate())
                    .assigmentPath(filePath)
                    .course(course)
                    .build();

            assignmentRepository.save(assignment);
            assignmentDto.setId(assignment.getId());
            assignmentDto.setFilePath(assignment.getAssigmentPath());
            return assignmentDto;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<AssignmentDto> findAll() {
        List<AssignmentDto> allAssignmnets = new ArrayList<>();
        assignmentRepository.findAll().stream().forEach(assignment -> {
            AssignmentDto dto = AssignmentDto.builder()
                    .id(assignment.getId())
                    .title(assignment.getTitle())
                    .dueDate(assignment.getDueDate())
                    .courseId(assignment.getCourse().getId())
                    .description(assignment.getDescription())
                    .filePath(assignment.getAssigmentPath()).build();
            allAssignmnets.add(dto);
        });
        return allAssignmnets;
    }

    @Override
    public AssignmentDto update(AssignmentDto assignmentDto) {
        return null;
    }

    @Override
    public List<AssignmentDto> assignmentOfParticularCourse(Integer id) {
        List<AssignmentDto> assignedTasks = new ArrayList<>();
        Optional<List<Assignment>> optionalAssignments = assignmentRepository.findByCourseId(id);
        List<Assignment> assignments = optionalAssignments.get();
        if (assignments.size() != 0) {
            assignments.stream().forEach(assignment -> {
                AssignmentDto assignmentdto = AssignmentDto.builder()
                        .id(assignment.getId())
                        .title(assignment.getTitle())
                        .dueDate(assignment.getDueDate())
                        .courseId(assignment.getCourse().getId())
                        .description(assignment.getDescription())
                        .filePath(assignment.getAssigmentPath())
                        .build();
                assignedTasks.add(assignmentdto);
            });
            return assignedTasks;
        } else {
            return null;
        }
    }


}
