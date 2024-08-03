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
@Table(name = "tbl_role")
public class Role implements Serializable {
    @Id
    @SequenceGenerator(name = "tbl_role_id_sequence", initialValue = 1, sequenceName = "tbl_role_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_role_id_sequence")
    private Integer id;

    @Column(name = "role_name", length = 50)
    private String roleName;

}
