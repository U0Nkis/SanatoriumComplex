package ru.vsu.cs.trufanov.sanatoriumcomplex.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.HotelRoom;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Services.HotelRoomService;

import java.util.Optional;

@Controller
@RequestMapping("/hotel-room")
public class HotelRoomController {
    @Autowired
    private HotelRoomService hotelRoomService;

    @GetMapping
    public String listHotelRoom(Model model) {
        model.addAttribute("hotelRoom", hotelRoomService.findAllHotelRoom());
        return "hotel-room/list";
    }

    @GetMapping("/add")
    public String showAddHotelRoomForm(Model model) {
        model.addAttribute("hotelRoom", new HotelRoom());
        return "hotel-room/form";
    }

    @PostMapping("/save")
    public String saveHotelRoom(@ModelAttribute("hotelRoom") HotelRoom hotelRoom, BindingResult result) {
        if (result.hasErrors()) {
            return "hotel-room/form";
        }
        hotelRoomService.saveHotelRoom(hotelRoom);
        return "redirect:/hotel-room";
    }

    @GetMapping("/edit/{id}")
    public String showEditHotelRoomForm(@PathVariable("id") Integer id, Model model) {
        Optional<HotelRoom> hotelRoom = hotelRoomService.findHotelRoomById(id);
        if (hotelRoom.isPresent()) {
            model.addAttribute("hotelRoom", hotelRoom.get());
            return "hotel-room/edit-form";
        } else {
            return "redirect:/hotel-room";
        }
    }

    @PostMapping("edit/{id}")
    public String updateHotelRoom(@PathVariable("id") Integer id, @ModelAttribute("hotelRoom") HotelRoom hotelRoom) {
        hotelRoomService.saveHotelRoom(hotelRoom);
        return "redirect:/hotel-room";
    }

    @GetMapping("/delete/{id}")
    public String deleteHotelRoom(@PathVariable("id") Integer id) {
        hotelRoomService.deleteHotelRoom(id);
        return "redirect:/hotel-room";
    }
}
