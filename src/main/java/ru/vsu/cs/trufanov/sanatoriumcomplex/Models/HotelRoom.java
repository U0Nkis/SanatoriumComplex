package ru.vsu.cs.trufanov.sanatoriumcomplex.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "hotelrooms")
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotelrooms_id_gen")
    @SequenceGenerator(name = "hotelrooms_id_gen", sequenceName = "hotelrooms_hotel_room_id_seq", allocationSize = 1)
    @Column(name = "hotel_room_id", nullable = false)
    private Integer id;

    @Column(name = "room_number", nullable = false, length = 20)
    private String roomNumber;

    @Column(name = "bed_count", nullable = false)
    private Integer bedCount;

    @Column(name = "price_per_night", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerNight;
}