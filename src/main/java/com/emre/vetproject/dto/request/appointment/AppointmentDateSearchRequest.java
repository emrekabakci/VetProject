package com.emre.vetproject.dto.request.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDateSearchRequest {
    private LocalDateTime startHour;
    private LocalDateTime finishHour;
}
