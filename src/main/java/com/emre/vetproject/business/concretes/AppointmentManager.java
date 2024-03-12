package com.emre.vetproject.business.concretes;

import com.emre.vetproject.business.abstracts.IAppointmentService;
import com.emre.vetproject.core.exception.NotFoundException;
import com.emre.vetproject.core.utilities.Message;
import com.emre.vetproject.dao.IAppointmentRepo;
import com.emre.vetproject.dto.request.appointment.AppointmentSaveRequest;
import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Appointment;
import com.emre.vetproject.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AppointmentManager implements IAppointmentService {

    private final IAppointmentRepo appointmentRepo;

    public AppointmentManager(IAppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }


    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    @Override
    public Appointment get(long id) {
        return appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Appointment update(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(long id) {
        Appointment appointment = get(id);
        appointmentRepo.delete(appointment);
        return true;
    }

    @Override
    public Page<Appointment> doctorCursor(Doctor doctor, LocalDateTime start, LocalDateTime finish, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return appointmentRepo.findAppointmentsByDoctorAndAppointmentDateBetween(doctor, start, finish, pageable);
    }

    @Override
    public Page<Appointment> animalCursor(Animal animal, LocalDateTime start, LocalDateTime finish, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return appointmentRepo.findAppointmentsByAnimalAndAppointmentDateBetween(animal, start, finish, pageable);
    }

    @Override
    public void isHourAvailable(Doctor doctor, AppointmentSaveRequest appointmentSaveRequest) {
        LocalDateTime appointmentDate = appointmentSaveRequest.getAppointmentDate();
        LocalDateTime truncatedDate = appointmentDate.truncatedTo(ChronoUnit.HOURS);
        if (!truncatedDate.isEqual(appointmentDate)) {
            throw new RuntimeException("Randevular saat başı olarak girilmek zorundadır!");
        }
        boolean isExists = appointmentRepo.existsByDoctorAndAppointmentDate(doctor, appointmentDate);
        if (isExists) {
            throw new RuntimeException("Bu saatte bir randevu bulunmakta!");
        }

    }

}
