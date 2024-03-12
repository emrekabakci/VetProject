package com.emre.vetproject.dao;

import com.emre.vetproject.model.AvailableDate;
import com.emre.vetproject.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    Page<AvailableDate> findAvailableDateByDoctor(Doctor doctor, Pageable pageable);

    boolean existsByDoctorAndAvailableDate(Doctor doctor, LocalDate localDate);
}
