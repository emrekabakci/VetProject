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
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String code;
    LocalDate protectionStartDate;
    LocalDate protectionFinishDate;
    @ManyToOne()
    @JoinColumn(name = "animal_id")
    Animal animal;
}
