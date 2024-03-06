package com.emre.vetproject.api;

import com.emre.vetproject.business.abstracts.IAnimalService;
import com.emre.vetproject.business.abstracts.ICustomerService;
import com.emre.vetproject.core.config.modelMapper.IModelMapperService;
import com.emre.vetproject.core.result.Result;
import com.emre.vetproject.core.result.ResultData;
import com.emre.vetproject.core.utilities.ResultGen;
import com.emre.vetproject.dto.request.customer.CustomerSaveRequest;
import com.emre.vetproject.dto.request.customer.CustomerUpdateRequest;
import com.emre.vetproject.dto.response.CursorResponse;
import com.emre.vetproject.dto.response.animal.AnimalResponse;
import com.emre.vetproject.dto.response.customer.CustomerResponse;
import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper, IAnimalService animalService) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerService.save(saveCustomer);

        return ResultGen.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") int id) {
        Customer customer = this.customerService.get(id);
        return ResultGen.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pagesize", required = false, defaultValue = "2") int pageSize) {

        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));

        return ResultGen.cursor(customerResponsePage);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.update(updateCustomer);

        return ResultGen.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.customerService.delete(id);
       return ResultGen.ok();
    }

    @GetMapping("/{customerId}/animals")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAllAnimalsByCustomer(@PathVariable("customerId") int customerId) {
        List<Animal> animals = this.customerService.getAllAnimalsByCustomer(customerId);
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());

        return ResultGen.success(animalResponses);
    }

    @GetMapping("/searchbyname/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> findCustomersByName(@PathVariable("name") String name) {
        List<Customer> customers = this.customerService.findCustomersByName(name);
        List<CustomerResponse> customerResponseList = customers.stream().map(customer -> this.modelMapper.forResponse()
                        .map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
        return ResultGen.success(customerResponseList);
    }

}
