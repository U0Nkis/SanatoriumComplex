package ru.vsu.cs.trufanov.sanatoriumcomplex.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "customerprocedures")
public class CustomerProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerprocedures_id_gen")
    @SequenceGenerator(name = "customerprocedures_id_gen", sequenceName = "customerprocedures_customer_procedure_id_seq", allocationSize = 1)
    @Column(name = "customer_procedure_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "customer_id", nullable = false)
    private ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "procedure_id", nullable = false)
    private Procedures procedure;

    @Column(name = "procedure_date", nullable = false)
    private LocalDate procedureDate;
}