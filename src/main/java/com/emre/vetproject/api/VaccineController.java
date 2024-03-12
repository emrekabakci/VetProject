package com.emre.vetproject.api;

import com.emre.vetproject.business.abstracts.IAnimalService;
import com.emre.vetproject.business.abstracts.IVaccineService;
import com.emre.vetproject.core.config.modelMapper.IModelMapperService;
import com.emre.vetproject.core.result.Result;
import com.emre.vetproject.core.result.ResultData;
import com.emre.vetproject.core.utilities.ResultGen;
import com.emre.vetproject.dto.request.vaccine.VaccineDateSearchRequest;
import com.emre.vetproject.dto.request.vaccine.VaccineSaveRequest;
import com.emre.vetproject.dto.request.vaccine.VaccineUpdateRequest;
import com.emre.vetproject.dto.response.CursorResponse;
import com.emre.vetproject.dto.response.animal.AnimalResponse;
import com.emre.vetproject.dto.response.vaccine.VaccineResponse;
import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    private final IVaccineService vaccineService;
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;

    public VaccineController(IVaccineService vaccineService, IAnimalService animalService, IModelMapperService modelMapper) {
        this.vaccineService = vaccineService;
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save/{animalId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@PathVariable(name = "animalId") int animalId, @Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        Animal animal = animalService.get(animalId);
        vaccineService.checkVaccine(animal, vaccineSaveRequest);
        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        saveVaccine.setAnimal(animal);
        this.vaccineService.save(saveVaccine);

        return ResultGen.created(this.modelMapper.forResponse().map(saveVaccine, VaccineResponse.class));
    }


//    @PostMapping("/animals/{animalId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResultData<VaccineResponse> saveVaccineToAnimal(@PathVariable("vaccineId") int vaccineId, @PathVariable("animalId") int animalId) {
//        Vaccine vaccine = this.vaccineService.get(vaccineId);
//        Animal animal = this.animalService.get(animalId);
//
//        if (vaccine == null || animal == null) {
//            throw new RuntimeException("Böyle bir hasta ya da aşı verisi bulunamadı!");
//        }
//
//        animal.setVaccine(vaccine);
//        this.animalService.save(animal);
//
//        return ResultGen.created(this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
//    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") int id) {
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultGen.success(this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
    }

    @GetMapping("/list/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @PathVariable(name = "animalId") int animalId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pagesize", required = false, defaultValue = "2") int pageSize) {

        Animal animal = animalService.get(animalId);
        Page<Vaccine> vaccinePage = this.vaccineService.cursor(animal, page, pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));

        return ResultGen.cursor(vaccineResponsePage);
    }

    @PostMapping("/listbydaterange")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<Animal>> listbydaterange(
            @RequestBody VaccineDateSearchRequest vaccineDateSearchRequest) {

        Set<Animal> animals = this.vaccineService.searchByDateRange(vaccineDateSearchRequest);
        List<Animal> result = new ArrayList<>();
        result.addAll(animals);
        return ResultGen.success(result);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        this.vaccineService.update(updateVaccine);

        return ResultGen.success(this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.vaccineService.delete(id);
        return ResultGen.ok();
    }

    @GetMapping("/{vaccineId}/animals")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAllAnimalsByVaccine(@PathVariable("vaccineId") int vaccineId) {
        List<Animal> animals = this.vaccineService.getAllAnimalsByVaccine(vaccineId);
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());

        return ResultGen.success(animalResponses);
    }

}
