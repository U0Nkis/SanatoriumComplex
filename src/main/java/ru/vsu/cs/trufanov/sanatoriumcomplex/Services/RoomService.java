package ru.vsu.cs.trufanov.sanatoriumcomplex.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Procedures;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Repository.api.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Procedures> findAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Procedures> findRoomById(Integer id) {
        return roomRepository.findById(id);
    }

    public Procedures saveRoom(Procedures room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }
}
