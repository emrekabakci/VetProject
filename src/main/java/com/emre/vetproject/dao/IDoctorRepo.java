package com.emre.vetproject.dao;

import com.emre.vetproject.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
}
