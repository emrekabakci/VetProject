package com.emre.vetproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String species;
    String breed;
    String gender;
    String colour;
    LocalDate dateOfBirth;
    @ManyToOne()
    @JoinColumn(name = "animal_customer_id")
    Customer customer;
    @OneToMany(mappedBy = "animal")
    List<Vaccine> vaccine;
}
