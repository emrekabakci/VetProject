package com.emre.vetproject.business.concretes;

import com.emre.vetproject.business.abstracts.IAnimalService;
import com.emre.vetproject.core.exception.NotFoundException;
import com.emre.vetproject.core.utilities.Message;
import com.emre.vetproject.dao.IAnimalRepo;
import com.emre.vetproject.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements IAnimalService {

    private final IAnimalRepo animalRepo;

    public AnimalManager(IAnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }

    @Override
    public Animal save(Animal animal) {
        return animalRepo.save(animal);
    }

    @Override
    public Animal get(long id) {
        return animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return animalRepo.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        return animalRepo.save(animal);
    }

    @Override
    public boolean delete(long id) {
        Animal animal = get(id);
        animalRepo.delete(animal);
        return true;
    }

    @Override
    public List<Animal> findAnimalsByName(String name) {
        return animalRepo.findAnimalsByName(name);
    }

}
