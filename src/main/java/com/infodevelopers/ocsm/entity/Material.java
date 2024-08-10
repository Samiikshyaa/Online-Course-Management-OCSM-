package com.infodevelopers.ocsm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "tbl_material")
public class Material implements Serializable {
    @Id
    @SequenceGenerator(name = "tbl_material_id_sequence", allocationSize = 1, sequenceName = "tbl_material_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_material_id_sequence")
    private Integer id;
    @Column(name = "title", length = 200)
    private String title;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "file_path", length = 200)
    private String materialPath;
    @ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_MATERIAL_COURSE"))
    private Course course;
}
