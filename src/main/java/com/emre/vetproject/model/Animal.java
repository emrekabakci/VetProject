package com.emre.vetproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    @ManyToOne()
    @JoinColumn(name = "animal_customer_id")
    @JsonManagedReference
    private Customer customer;
    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Vaccine> vaccine;
}
