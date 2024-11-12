package ru.vsu.cs.trufanov.sanatoriumcomplex.Repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.RoomUsage;

@Repository
public interface RoomUsageRepository extends JpaRepository<RoomUsage, Integer> {
}
