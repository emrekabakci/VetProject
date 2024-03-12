package com.emre.vetproject.business.abstracts;

import com.emre.vetproject.dto.request.appointment.AppointmentSaveRequest;
import com.emre.vetproject.model.Appointment;
import com.emre.vetproject.model.AvailableDate;
import com.emre.vetproject.model.Doctor;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    AvailableDate save(AvailableDate availableDate);

    AvailableDate get(long id);

    AvailableDate update(AvailableDate availableDate);

    boolean delete(long id);

    Page<AvailableDate> cursor(Doctor doctor, int page, int pageSize);

    void isDateAvailable(Doctor doctor, AppointmentSaveRequest appointmentSaveRequest);

    void isHourAvailable(Appointment appointment, AppointmentSaveRequest appointmentSaveRequest);
}
