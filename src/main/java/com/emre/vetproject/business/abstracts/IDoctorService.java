package com.emre.vetproject.business.abstracts;

import com.emre.vetproject.model.Doctor;
import com.emre.vetproject.model.Vaccine;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface IDoctorService {
    Doctor save(Doctor doctor);
    Doctor get(int id);
    Page<Doctor> cursor(int page, int pageSize);

    Doctor update(Doctor doctor);

    boolean delete(int id);
}
