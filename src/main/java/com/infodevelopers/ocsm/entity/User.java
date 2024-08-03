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
@Table(name = "tbl_user", uniqueConstraints = {@UniqueConstraint(name = "unique_constraint_user_email", columnNames = "email"),
        @UniqueConstraint(name = "unique_constraint_user_mobile", columnNames = "mobile_number")})

public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "tbl_user_id_sequence", sequenceName = "tbl_user_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_user_id_sequence")
    private Integer id;

    @Column(name = "user_name", length = 150, nullable = false)
    private String userName;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "mobile_number", length = 10)
    private String mobileNumber;

    @OneToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ROLE_USER_ID")),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ROLE_ROLE_ID")),
            uniqueConstraints = @UniqueConstraint(name = "user_role_unique", columnNames = {"user_id", "role_id"}))
    private List<Role> role;
}
