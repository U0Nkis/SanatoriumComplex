package ru.vsu.cs.trufanov.sanatoriumcomplex.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Staff;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Services.StaffService;

import java.util.Optional;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("staff", staffService.findAllStaff());
        return "staff/list";
    }

    @GetMapping("/add")
    public String showAddStaffForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff/form";
    }

    @PostMapping
    public String saveStaff(@ModelAttribute("staff")Staff staff, BindingResult result) {
        if (result.hasErrors()) {
            return "staff/form";
        }
        staffService.saveStaff(staff);
        return "redirect:/staff";
    }

    @GetMapping("edit/{id}")
    public String showEditStaffForm(@PathVariable("id") Integer id, Model model) {
        Optional<Staff> staff = staffService.findStaffById(id);
        if (staff.isPresent()) {
            model.addAttribute("staff", staff.get());
            return "staff/edit-form";
        } else {
            return "redirect:/staff";
        }
    }

    @PostMapping("edit/{id}")
    public String updateStaff(@PathVariable("id") Integer id, @ModelAttribute("staff")Staff staff) {
        staffService.saveStaff(staff);
        return "redirect:/staff";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable("id") Integer id) {
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }
}
