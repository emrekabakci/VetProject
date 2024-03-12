package com.emre.vetproject.dao;

import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Appointment;
import com.emre.vetproject.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
    Page<Appointment> findAppointmentsByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDateTime start, LocalDateTime finish, Pageable pageable);

    Page<Appointment> findAppointmentsByAnimalAndAppointmentDateBetween(Animal animal, LocalDateTime start, LocalDateTime finish, Pageable pageable);

    boolean existsByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime localDateTime);


}
