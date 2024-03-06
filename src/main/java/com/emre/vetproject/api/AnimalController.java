package com.emre.vetproject.api;

import com.emre.vetproject.business.abstracts.IAnimalService;
import com.emre.vetproject.business.abstracts.ICustomerService;
import com.emre.vetproject.core.config.modelMapper.IModelMapperService;
import com.emre.vetproject.core.result.Result;
import com.emre.vetproject.core.result.ResultData;
import com.emre.vetproject.core.utilities.ResultGen;
import com.emre.vetproject.dto.request.animal.AnimalSaveRequest;
import com.emre.vetproject.dto.request.animal.AnimalUpdateRequest;
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
@RequestMapping("animal")
public class AnimalController {
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;
    private final ICustomerService customerService;

    public AnimalController(
            IAnimalService animalService,
            IModelMapperService modelmapper,
            ICustomerService customerService
    ) {
        this.animalService = animalService;
        this.modelMapper = modelmapper;
        this.customerService = customerService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@RequestBody AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);

        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        saveAnimal.setCustomer(customer);

        this.animalService.save(saveAnimal);
        return ResultGen.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") int id) {
        Animal animal = this.animalService.get(id);
        return ResultGen.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pagesize", required = false, defaultValue = "2") int pageSize) {

        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));

        return ResultGen.cursor(animalResponsePage);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        this.animalService.update(updateAnimal);

        return ResultGen.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.animalService.delete(id);
        return ResultGen.ok();
    }

    @GetMapping("/searchbyname/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> findAnimalsByName(@PathVariable("name") String name) {
        List<Animal> animals = this.animalService.findAnimalsByName(name);
        List<AnimalResponse> animalResponseList = animals.stream().map(customer -> this.modelMapper.forResponse()
                        .map(animals, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultGen.success(animalResponseList);
    }

}
