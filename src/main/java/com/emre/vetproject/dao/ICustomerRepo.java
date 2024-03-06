package com.emre.vetproject.dao;

import com.emre.vetproject.dto.response.customer.CustomerResponse;
import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findCustomersByName(String name);
}
