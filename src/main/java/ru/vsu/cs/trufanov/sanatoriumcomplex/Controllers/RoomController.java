package ru.vsu.cs.trufanov.sanatoriumcomplex.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Room;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Services.RoomService;

import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.findAllRooms());
        return "rooms/list";
    }

    @GetMapping("/add")
    public String showAddRoomsForm(Model model) {
        model.addAttribute("room", new Room());
        return "rooms/form";
    }

    @PostMapping("/save")
    public String saveRoom(@ModelAttribute("room") Room room, BindingResult result) {
        if (result.hasErrors()) {
            return "rooms/form";
        }
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoomForm(@PathVariable("id") Integer id, Model model) {
        Optional<Room> room = roomService.findRoomById(id);
        if (room.isPresent()) {
            model.addAttribute("room", room.get());
            return "rooms/edit-form";
        } else {
            return "redirect:/rooms";
        }
    }

    @PostMapping("edit/{id}")
    public String updateRoom(@PathVariable("id") Integer id, @ModelAttribute("room") Room room) {
        Optional<Room> existingRoomOpt = roomService.findRoomById(id);
        if (existingRoomOpt.isPresent()) {
            Room existingRoom = existingRoomOpt.get();
            room.setRoomNumber(existingRoom.getRoomNumber());
            room.setId(existingRoom.getId());
            roomService.saveRoom(room);
        }
        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Integer id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}
