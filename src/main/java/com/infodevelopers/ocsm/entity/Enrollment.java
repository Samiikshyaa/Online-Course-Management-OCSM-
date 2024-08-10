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
@Table(name = "tbl_enrollment" , uniqueConstraints = {
        @UniqueConstraint(name = "UniqueUserAndCourse", columnNames = {"user_id", "course_id"})})
public class Enrollment implements Serializable {
    @Id
    @SequenceGenerator(name = "tbl_enrollment_id_sequence", initialValue = 1, sequenceName = "tbl_enrollment_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "enrolled_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enrolledAt;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id" ,foreignKey = @ForeignKey(name = "FK_ENROLLMENT_USER"))
    private User user;

    @ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ENROLLMENT_COURSE"))
    private Course course;

}
