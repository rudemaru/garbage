package com.main.laba_1.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "faculty", nullable = false)
    private String faculty;

    @Column(name = "speciality", nullable = false)
    private String speciality;

    @JsonIgnore
    @ManyToMany(mappedBy = "savedGroups")
    private Set<User> savedUsers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private Set<User> groupUsers = new HashSet<>();
}
