package ru.vsu.cs.trufanov.sanatoriumcomplex.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.RoomUsage;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Repository.api.RoomUsageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomUsageService {
    @Autowired
    RoomUsageRepository roomUsageRepository;

    public List<RoomUsage> findAllRoomUsage() {
        return roomUsageRepository.findAll().stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).toList();
    }

    public Optional<RoomUsage> findRoomUsageById(Integer id) {
        return roomUsageRepository.findById(id);
    }

    public RoomUsage saveRoomUsage(RoomUsage roomUsage) {
        return roomUsageRepository.save(roomUsage);
    }

    public void deleteRoomUsage(Integer id) {
        roomUsageRepository.deleteById(id);
    }
}
