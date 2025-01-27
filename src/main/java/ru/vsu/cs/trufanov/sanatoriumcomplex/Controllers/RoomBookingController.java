package ru.vsu.cs.trufanov.sanatoriumcomplex.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.RoomBooking;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Services.RoomBookingService;

import java.util.Optional;

@Controller
@RequestMapping("room-booking")
public class RoomBookingController {
    @Autowired
    private RoomBookingService roomBookingService;

    @GetMapping
    public String listRoomBooking(Model model) {
        model.addAttribute("roomBooking", roomBookingService.findAllRoomBooking());
        return "room-booking/list";
    }

    @GetMapping("/add")
    public String showAddRoomBookingForm(Model model) {
        model.addAttribute("roomBooking", new RoomBooking());
        return "room-booking/form";
    }

    @PostMapping("/save")
    public String saveRoomBooking(@ModelAttribute("roomBooking") RoomBooking roomBooking, BindingResult result) {
        if (result.hasErrors()) {
            return "room-booking/form";
        }
        roomBookingService.saveRoomBooking(roomBooking);
        return "redirect:/room-booking";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoomBookingForm(@PathVariable("id") Integer id, Model model) {
        Optional<RoomBooking> roomBooking = roomBookingService.findRoomBookingById(id);
        if (roomBooking.isPresent()) {
            model.addAttribute("roomBooking", roomBooking.get());
            return "room-booking/edit-form";
        } else {
            return "redirect:/room-booking";
        }
    }

    @PostMapping("edit/{id}")
    public String updateRoomBooking(@PathVariable("id") Integer id, @ModelAttribute("roomBooking") RoomBooking roomBooking) {
        roomBookingService.saveRoomBooking(roomBooking);
        return "redirect:/room-booking";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoomBooking(@PathVariable("id") Integer id) {
        roomBookingService.deleteRoomBooking(id);
        return "redirect:/room-booking";
    }
}
