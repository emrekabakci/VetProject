package com.emre.vetproject.dao;

import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {
    List<Animal> findByCustomerId(long customerId);

    List<Animal> findAnimalsByName(String name);

    Animal findAnimalByVaccine(Vaccine vaccine);

}
