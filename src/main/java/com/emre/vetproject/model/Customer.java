package com.emre.vetproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Animal> animals;
}
