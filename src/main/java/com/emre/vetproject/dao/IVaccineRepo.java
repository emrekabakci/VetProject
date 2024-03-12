package com.emre.vetproject.dao;

import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine, Long> {
    Page<Vaccine> findVaccinesByAnimal(Animal animal, Pageable pageable);

    List<Vaccine> findVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate);
}
