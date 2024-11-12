package ru.vsu.cs.trufanov.sanatoriumcomplex.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Procedures;
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
        model.addAttribute("room", new Procedures());
        return "rooms/form";
    }

    @PostMapping("/save")
    public String saveRoom(@ModelAttribute("room") Procedures room, BindingResult result) {
        if (result.hasErrors()) {
            return "rooms/form";
        }
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoomForm(@PathVariable("id") Integer id, Model model) {
        Optional<Procedures> room = roomService.findRoomById(id);
        if (room.isPresent()) {
            model.addAttribute("room", room.get());
            return "rooms/form";
        } else {
            return "redirect:/rooms";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Integer id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}
