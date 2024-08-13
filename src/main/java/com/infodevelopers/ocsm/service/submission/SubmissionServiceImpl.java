package com.infodevelopers.ocsm.service.submission;

import com.infodevelopers.ocsm.dto.submissionDto.SubmissionDto;
import com.infodevelopers.ocsm.entity.Assignment;
import com.infodevelopers.ocsm.entity.Submission;
import com.infodevelopers.ocsm.entity.User;
import com.infodevelopers.ocsm.projection.SubmissionProjection;
import com.infodevelopers.ocsm.repository.AssignmentRepository;
import com.infodevelopers.ocsm.repository.SubmissionRepository;
import com.infodevelopers.ocsm.repository.UserRepository;
import com.infodevelopers.ocsm.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository, AssignmentRepository assignmentRepository, UserRepository userRepository, FileStorageService fileStorageService) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }


    @Override
    public SubmissionDto create(SubmissionDto submissionDto) throws Exception {
        Assignment assignment = assignmentRepository.findById(submissionDto.getAssignmentId()).orElseThrow();
        User user = userRepository.findById(submissionDto.getUserId()).orElseThrow();
        String filePath = fileStorageService.storeFile(submissionDto.getFile());
        boolean isStudent = user.getRole().stream().anyMatch(role -> role.getRoleName().equalsIgnoreCase("student"));
        if(isStudent){
            Submission submission = Submission.builder()
                    .submissionDate(submissionDto.getSubmissionDate())
                    .grade(submissionDto.getGrade())
                    .assignment(assignment)
                    .user(user)
                    .filePath(filePath)
                    .build();
            submissionRepository.save(submission);
            submissionDto.setId(submission.getId());
            submissionDto.setFilePath(submission.getFilePath());
            return submissionDto;
        }else{
            log.error("User is not the student");
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<SubmissionDto> findAll() {
//        List<SubmissionProjection> allSubmissions = submissionRepository.findAllProjections();
//        List<SubmissionDto> submissionDtos = new ArrayList<>();
//        allSubmissions.stream().forEach(submission -> {
//            submissionDtos.add(SubmissionDto.builder()
//                .id(submission.getId())
//                .submissionDate(submission.getSubmissionDate())
//                .assignmentId(submission.getAssignmentId())
//                .userId(submission.getUserId())
//                .filePath(submission.getFilePath())
//                .build());
//        });
//        return submissionDtos;
        return null;
    }

    @Override
    public SubmissionDto update(SubmissionDto submissionDto) {
        return null;
    }

    @Override
    public SubmissionDto gradeAssignment(Integer assignmentId,Integer userId, String grade) {
        Submission submission = submissionRepository.findByAssignmentIdAndUserId(assignmentId, userId);
        submission.setGrade(grade);
        submissionRepository.save(submission);
        return SubmissionDto.builder()
                .id(submission.getId())
                .submissionDate(submission.getSubmissionDate())
                .grade(submission.getGrade())
                .assignmentId(submission.getAssignment().getId())
                .userId(submission.getUser().getId())
                .build();
    }

    @Override
    public List<SubmissionDto> viewSubmissionsByAssignmentId(Integer assignmentId) {
        List<SubmissionDto> dtoList = new ArrayList<>();
        submissionRepository.findByAssignmentId(assignmentId).stream().forEach(submission -> {
            dtoList.add(SubmissionDto.builder()
                    .id(submission.getId())
                    .submissionDate(submission.getSubmissionDate())
                    .grade(submission.getGrade())
                    .assignmentId(submission.getAssignment().getId())
                    .userId(submission.getUser().getId())
                    .filePath(submission.getFilePath())
                    .build());
        });
        return dtoList;
    }

    @Override
    public List<SubmissionProjection> findAllSubmissions() {
        return submissionRepository.findAllProjections();
    }


}
