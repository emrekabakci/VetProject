package com.emre.vetproject.business.abstracts;

import com.emre.vetproject.dto.request.vaccine.VaccineDateSearchRequest;
import com.emre.vetproject.dto.request.vaccine.VaccineSaveRequest;
import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Customer;
import com.emre.vetproject.model.Vaccine;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface IVaccineService {
    Vaccine save(Vaccine vaccine);
    void checkVaccine(Animal animal, VaccineSaveRequest vaccineSaveRequest);
    Vaccine get(int id);
    Page<Vaccine> cursor(Animal animal,int page, int pageSize);

    Set<Animal> searchByDateRange(VaccineDateSearchRequest vaccineDateSearchRequest);

    Vaccine update(Vaccine vaccine);

    boolean delete(int id);

    List<Animal> getAllAnimalsByVaccine(int vaccineId);


}
