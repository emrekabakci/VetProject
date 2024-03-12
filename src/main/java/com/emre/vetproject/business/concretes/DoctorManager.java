package com.emre.vetproject.business.concretes;

import com.emre.vetproject.business.abstracts.IDoctorService;
import com.emre.vetproject.core.exception.NotFoundException;
import com.emre.vetproject.core.utilities.Message;
import com.emre.vetproject.dao.IDoctorRepo;
import com.emre.vetproject.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements IDoctorService {

    private final IDoctorRepo doctorRepo;

    public DoctorManager(IDoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    @Override
    public Doctor get(long id) {
        return doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return doctorRepo.findAll(pageable);
    }

    @Override
    public Doctor update(Doctor doctor) {
        get(doctor.getId());
        return doctorRepo.save(doctor);
    }

    @Override
    public boolean delete(long id) {
        Doctor doctor = get(id);
        doctorRepo.delete(doctor);
        return true;
    }
}
