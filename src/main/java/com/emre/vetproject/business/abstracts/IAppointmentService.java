package com.emre.vetproject.business.abstracts;

import com.emre.vetproject.dto.request.appointment.AppointmentSaveRequest;
import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Appointment;
import com.emre.vetproject.model.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface IAppointmentService {
    Appointment save(Appointment appointment);

    Appointment get(long id);

    Appointment update(Appointment appointment);

    boolean delete(long id);

    Page<Appointment> doctorCursor(Doctor doctor, LocalDateTime start, LocalDateTime finish, int page, int pageSize);

    Page<Appointment> animalCursor(Animal animal, LocalDateTime start, LocalDateTime finish, int page, int pageSize);

    void isHourAvailable(Doctor doctor, AppointmentSaveRequest appointmentSaveRequest);

}
