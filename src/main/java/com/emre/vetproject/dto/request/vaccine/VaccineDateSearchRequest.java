package com.emre.vetproject.dto.request.vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineDateSearchRequest {
    private LocalDate startDate;
    private LocalDate finishDate;
}
