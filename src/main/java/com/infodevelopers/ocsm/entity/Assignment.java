package com.infodevelopers.ocsm.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "tbl_assignment", uniqueConstraints = @UniqueConstraint(name = "Unique_title_courseId", columnNames = {"title","course_id"}))
public class Assignment implements Serializable {
    @Id
    @SequenceGenerator(name = "tbl_assignment_id_sequence", sequenceName = "tbl_assignment_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_assignment_id_sequence")
    private Integer id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "assignment_path", columnDefinition = "TEXT")
    private String assigmentPath;

    @ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ASSIGNMENT_COURSE_ID"))
    private Course course;

//    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY)
//    private List<Submission> submission;
}

