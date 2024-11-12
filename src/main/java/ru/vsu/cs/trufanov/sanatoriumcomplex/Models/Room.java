package ru.vsu.cs.trufanov.sanatoriumcomplex.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rooms_id_gen")
    @SequenceGenerator(name = "rooms_id_gen", sequenceName = "rooms_room_id_seq", allocationSize = 1)
    @Column(name = "room_id", nullable = false)
    private Integer id;

    @Column(name = "room_number", nullable = false, length = 20)
    private String roomNumber;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;
}