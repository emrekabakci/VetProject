package com.emre.vetproject.business.concretes;

import com.emre.vetproject.business.abstracts.IAvailableDateService;
import com.emre.vetproject.core.exception.NotFoundException;
import com.emre.vetproject.core.utilities.Message;
import com.emre.vetproject.dao.IAvailableDateRepo;
import com.emre.vetproject.dto.request.appointment.AppointmentSaveRequest;
import com.emre.vetproject.model.Appointment;
import com.emre.vetproject.model.AvailableDate;
import com.emre.vetproject.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final IAvailableDateRepo availableDateRepo;

    public AvailableDateManager(IAvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(long id) {
        return availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        return availableDateRepo.save(availableDate);
    }

    @Override
    public boolean delete(long id) {
        AvailableDate availableDate = get(id);
        availableDateRepo.delete(availableDate);
        return true;
    }

    @Override
    public Page<AvailableDate> cursor(Doctor doctor, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return availableDateRepo.findAvailableDateByDoctor(doctor, pageable);
    }

    @Override
    public void isDateAvailable(Doctor doctor, AppointmentSaveRequest appointmentSaveRequest) {
        boolean isAvailable = availableDateRepo.existsByDoctorAndAvailableDate(doctor, appointmentSaveRequest.getAppointmentDate().toLocalDate());
        if (!isAvailable) {
            throw new RuntimeException("Doktorun bu tarih için müsait bir günü bulunmamaktadır!");
        }
    }

    @Override
    public void isHourAvailable(Appointment appointment, AppointmentSaveRequest appointmentSaveRequest) {

    }
}
