package com.emre.vetproject.business.abstracts;

import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {
    Animal save(Animal animal);
    Animal get(int id);
    Page<Animal> cursor(int page, int pageSize);

    Animal update(Animal animal);

    boolean delete(int id);
    List<Animal> findAnimalsByName(String name);
}
