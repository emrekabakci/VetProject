package com.emre.vetproject.dto.response.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private int id;
    private String name;
    private String breed;
    private String gender;
    private LocalDate dateOfBirth;
    private int customerId;
    private int vaccineId;
}
