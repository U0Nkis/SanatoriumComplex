package ru.vsu.cs.trufanov.sanatoriumcomplex.Repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
