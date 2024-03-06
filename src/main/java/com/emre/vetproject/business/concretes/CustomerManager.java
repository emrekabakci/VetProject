package com.emre.vetproject.business.concretes;

import com.emre.vetproject.business.abstracts.ICustomerService;
import com.emre.vetproject.core.exception.NotFoundException;
import com.emre.vetproject.core.utilities.Message;
import com.emre.vetproject.dao.IAnimalRepo;
import com.emre.vetproject.dao.ICustomerRepo;
import com.emre.vetproject.dto.response.customer.CustomerResponse;
import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements ICustomerService {

    private final ICustomerRepo customerRepo;
    private final IAnimalRepo animalRepo;

    public CustomerManager(ICustomerRepo customerRepo, IAnimalRepo animalRepo) {
        this.customerRepo = customerRepo;
        this.animalRepo = animalRepo;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer get(int id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepo.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }

    @Override
    public boolean delete(int id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }

    @Override
    public List<Animal> getAllAnimalsByCustomer(int customerId) {
        return animalRepo.findByCustomerId(customerId);
    }


    @Override
    public List<Customer> findCustomersByName(String name) {
        return this.customerRepo.findCustomersByName(name);
    }


}
