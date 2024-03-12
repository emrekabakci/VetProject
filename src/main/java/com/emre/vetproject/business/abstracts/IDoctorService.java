package com.emre.vetproject.business.abstracts;

import com.emre.vetproject.model.Doctor;
import org.springframework.data.domain.Page;


public interface IDoctorService {
    Doctor save(Doctor doctor);

    Doctor get(long id);

    Page<Doctor> cursor(int page, int pageSize);

    Doctor update(Doctor doctor);

    boolean delete(long id);
}
