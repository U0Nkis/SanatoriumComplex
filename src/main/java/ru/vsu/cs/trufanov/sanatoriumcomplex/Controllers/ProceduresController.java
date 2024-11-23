package ru.vsu.cs.trufanov.sanatoriumcomplex.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Procedures;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Services.ProceduresService;

import java.util.Optional;

@Controller
@RequestMapping("/procedures")
public class ProceduresController {
    @Autowired
    private ProceduresService proceduresService;

    @GetMapping
    public String listProcedures(Model model) {
        model.addAttribute("procedures", proceduresService.findAllProcedures());
        return "procedures/list";
    }

    @GetMapping("/add")
    public String showAddProceduresForm(Model model) {
        model.addAttribute("procedure", new Procedures());
        return "procedures/form";
    }

    @PostMapping("/save")
    public String saveProcedure(@ModelAttribute("procedure") Procedures procedures, BindingResult result) {
        if (result.hasErrors()) {
            return "procedures/form";
        }
        proceduresService.saveProcedures(procedures);
        return "redirect:/procedures";
    }

    @GetMapping("/edit/{id}")
    public String showEditProceduresForm(@PathVariable("id") Integer id, Model model) {
        Optional<Procedures> procedures = proceduresService.findProceduresById(id);
        if (procedures.isPresent()) {
            model.addAttribute("procedure", procedures.get());
            return "procedures/form";
        } else {
            return "redirect:/procedures";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProcedure(@PathVariable("id") Integer id) {
        proceduresService.deleteProcedure(id);
        return "redirect:/procedures";
    }
}
