package com.emre.vetproject.business.abstracts;

import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {
    Customer save(Customer customer);

    Customer get(long id);

    Page<Customer> cursor(int page, int pageSize);

    Customer update(Customer customer);

    boolean delete(long id);

    List<Animal> getAllAnimalsByCustomer(long customerId);

    List<Customer> findCustomersByName(String name);

}
