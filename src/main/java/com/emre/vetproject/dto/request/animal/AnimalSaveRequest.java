package com.emre.vetproject.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {
    private int id;
    @NotNull(message = "isim boş bırakılamaz!")
    private String name;
    @NotNull(message = "tür boş bırakılamaz!")
    private String breed;
    @NotNull(message = "cinsiyet boş bırakılamaz!")
    private String gender;
    @NotNull(message = "doğum tarihi boş bırakılamaz!")
    private LocalDate dateOfBirth;
    @NotNull
    private int customerId;
    private int vaccineId;
}
