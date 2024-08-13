package com.infodevelopers.ocsm.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "tbl_submission", uniqueConstraints = @UniqueConstraint(name = "unique_assignmentId_user_id", columnNames = {"assignment_id","user_id"}))
public class Submission implements Serializable {
    @Id
    @SequenceGenerator(name = "tbl_submission_id_sequence", allocationSize = 1, sequenceName = "tbl_submission_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_submission_id_sequence")
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "submission_date", nullable = false)
    private Date submissionDate;
    @Column(name = "grade", length = 10)
    private String grade;
    @ManyToOne(targetEntity = Assignment.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_SUBMISSION_ASSIGNMENT"))
    private Assignment assignment;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_SUBMISSION_USER"))
    private User user;
    @Column(name = "file_path", length = 200)
    private String filePath;
}
