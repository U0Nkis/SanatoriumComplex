package ru.vsu.cs.trufanov.sanatoriumcomplex.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Customer;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Services.CustomerService;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listCustomers(Model model, Pageable pageable) {
        Page<Customer> page = customerService.findAllCustomers(pageable);
        model.addAttribute("page", page);
        return "customers/list";
    }

    @GetMapping("/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customers/form";
        }
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("edit/{id}")
    public String showEditCustomerForm(@PathVariable("id") Integer id, Model model) {
        Optional<Customer> customer = customerService.findCustomerById(id);
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "customers/edit-form";
        } else {
            return "redirect:/customers";
        }
    }

    @PostMapping("edit/{id}")
    public String updateCustomer(@PathVariable("id") Integer id, @ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
