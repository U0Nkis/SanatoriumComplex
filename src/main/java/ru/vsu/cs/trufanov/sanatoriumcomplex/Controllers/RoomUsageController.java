package ru.vsu.cs.trufanov.sanatoriumcomplex.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.RoomUsage;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Services.RoomUsageService;

import java.util.Optional;

@Controller
@RequestMapping("room-usage")
public class RoomUsageController {
    @Autowired
    private RoomUsageService roomUsageService;

    @GetMapping
    public String listRoomUsage(Model model) {
        model.addAttribute("roomUsage", roomUsageService.findAllRoomUsage());
        return "room-usage/list";
    }

    @GetMapping("/add")
    public String showAddRoomUsageForm(Model model) {
        model.addAttribute("roomUsage", new RoomUsage());
        return "room-usage/form";
    }

    @PostMapping("/save")
    public String saveRoomUsage(@ModelAttribute("roomUsage") RoomUsage roomUsage, BindingResult result) {
        if (result.hasErrors()) {
            return "room-usage/form";
        }
        roomUsageService.saveRoomUsage(roomUsage);
        return "redirect:/room-usage";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoomUsageForm(@PathVariable("id") Integer id, Model model) {
        Optional<RoomUsage> roomUsage = roomUsageService.findRoomUsageById(id);
        if (roomUsage.isPresent()) {
            model.addAttribute("roomUsage", roomUsage.get());
            return "room-usage/edit-form";
        } else {
            return "redirect:/room-usage";
        }
    }

    @PostMapping("edit/{id}")
    public String updateRoomUsage(@PathVariable("id") Integer id, @ModelAttribute("roomUsage") RoomUsage roomUsage) {
        roomUsageService.saveRoomUsage(roomUsage);
        return "redirect:/room-usage";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoomUsage(@PathVariable("id") Integer id) {
        roomUsageService.deleteRoomUsage(id);
        return "redirect:/room-usage";
    }
}
