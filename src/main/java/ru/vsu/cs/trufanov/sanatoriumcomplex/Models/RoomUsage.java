package ru.vsu.cs.trufanov.sanatoriumcomplex.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "roomusage")
public class RoomUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomusage_id_gen")
    @SequenceGenerator(name = "roomusage_id_gen", sequenceName = "roomusage_usage_id_seq", allocationSize = 1)
    @Column(name = "usage_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "room_id", nullable = false)
    private Procedures room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "procedure_id", nullable = false)
    private Procedures procedure;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "staff_id", nullable = false)
    private ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Staff staff;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;
}