package com.infodevelopers.ocsm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder


@Entity
@Table(name = "tbl_course")
public class Course implements Serializable {
    @Id
    @SequenceGenerator(name = "tbl_course_sequence_generator", sequenceName = "tbl_course_sequence_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_course_sequence_generator")
    private Integer id;

    @Column(name = "course_name", length = 150, nullable = false)
    private String courseName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "schedule", length = 30, nullable = false)
    private String schedule;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinTable(name = "course_instructor",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_COURSE_ID")),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID")))
    private List<User> instructors;

}
