package com.emre.vetproject.business.concretes;

import com.emre.vetproject.business.abstracts.IVaccineService;
import com.emre.vetproject.core.exception.NotFoundException;
import com.emre.vetproject.core.utilities.Message;
import com.emre.vetproject.dao.IAnimalRepo;
import com.emre.vetproject.dao.IVaccineRepo;
import com.emre.vetproject.dto.request.vaccine.VaccineDateSearchRequest;
import com.emre.vetproject.dto.request.vaccine.VaccineSaveRequest;
import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Customer;
import com.emre.vetproject.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class VaccineManager implements IVaccineService {

    private final IVaccineRepo vaccineRepo;
    private final IAnimalRepo animalRepo;

    public VaccineManager(IVaccineRepo vaccineRepo, IAnimalRepo animalRepo) {
        this.vaccineRepo = vaccineRepo;
        this.animalRepo = animalRepo;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {

        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public void checkVaccine(Animal animal, VaccineSaveRequest vaccineSaveRequest) {
        List<Vaccine> vaccines = animal.getVaccine();
        for (Vaccine vaccine : vaccines) {
            if (vaccine.getName().equals(vaccineSaveRequest.getName())&&
                    vaccine.getCode().equals(vaccineSaveRequest.getCode())&& vaccine.getProtectionFinishDate().isAfter(LocalDate.now())) {
                throw new RuntimeException("Hayvanın aynı aşı türünden geçerli bir aşısı bulunmaktadır!");
            }
        }
    }

    @Override
    public Vaccine get(int id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }


    @Override
    public Page<Vaccine> cursor(Animal animal ,int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.vaccineRepo.findVaccinesByAnimal(animal,pageable);
    }

    @Override
    public Set<Animal> searchByDateRange(VaccineDateSearchRequest vaccineDateSearchRequest) {
        List<Vaccine> vaccines = vaccineRepo.findVaccinesByProtectionFinishDateBetween(vaccineDateSearchRequest.getStartDate(), vaccineDateSearchRequest.getFinishDate());
        Set<Animal> animals = new HashSet<>();
        for (Vaccine vaccine : vaccines) {
            animals.add(animalRepo.findAnimalByVaccine(vaccine));

        }
        return animals;
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public boolean delete(int id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    @Override
    public List<Animal> getAllAnimalsByVaccine(int vaccineId) {
        return animalRepo.findByCustomerId(vaccineId);
    }


}
