package ru.vsu.cs.trufanov.sanatoriumcomplex.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "procedures")
public class Procedures {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "procedures_id_gen")
    @SequenceGenerator(name = "procedures_id_gen", sequenceName = "procedures_procedure_id_seq", allocationSize = 1)
    @Column(name = "procedure_id", nullable = false)
    private Integer id;

    @Column(name = "procedure_name", nullable = false, length = 100)
    private String procedureName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;
}