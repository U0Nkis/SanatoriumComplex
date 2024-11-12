package ru.vsu.cs.trufanov.sanatoriumcomplex.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_id_gen")
    @SequenceGenerator(name = "staff_id_gen", sequenceName = "staff_staff_id_seq", allocationSize = 1)
    @Column(name = "staff_id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "specialization", nullable = false, length = 100)
    private String specialization;
}