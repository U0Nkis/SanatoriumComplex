package ru.vsu.cs.trufanov.sanatoriumcomplex.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.HotelRoom;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Repository.api.HotelRoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HotelRoomService {
    @Autowired
    HotelRoomRepository hotelRoomRepository;

    public List<HotelRoom> findAllHotelRoom() {
        return hotelRoomRepository.findAll().stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).toList();
    }

    public Optional<HotelRoom> findHotelRoomById(Integer id) {
        return hotelRoomRepository.findById(id);
    }

    public HotelRoom saveHotelRoom(HotelRoom hotelRoom) {
        return hotelRoomRepository.save(hotelRoom);
    }

    public void deleteHotelRoom(Integer id) {
        hotelRoomRepository.deleteById(id);
    }
}
