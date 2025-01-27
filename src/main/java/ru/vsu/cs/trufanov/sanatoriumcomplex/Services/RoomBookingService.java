package ru.vsu.cs.trufanov.sanatoriumcomplex.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.RoomBooking;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Staff;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Repository.api.RoomBookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomBookingService {
    @Autowired
    RoomBookingRepository roomBookingRepository;

    public List<RoomBooking> findAllRoomBooking() {
        return roomBookingRepository.findAll().stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).toList();
    }

    public Optional<RoomBooking> findRoomBookingById(Integer id) {
        return roomBookingRepository.findById(id);
    }

    public RoomBooking saveRoomBooking(RoomBooking roomBooking) {
        return roomBookingRepository.save(roomBooking);
    }

    public void deleteRoomBooking(Integer id) {
        roomBookingRepository.deleteById(id);
    }
}
