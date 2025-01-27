package ru.vsu.cs.trufanov.sanatoriumcomplex.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Room;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Repository.api.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAllRooms() {
        return roomRepository.findAll().stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).toList();
    }

    public Optional<Room> findRoomById(Integer id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }
}
