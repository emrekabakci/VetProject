package com.emre.vetproject.api;

import com.emre.vetproject.business.abstracts.IDoctorService;
import com.emre.vetproject.core.config.modelMapper.IModelMapperService;
import com.emre.vetproject.core.result.Result;
import com.emre.vetproject.core.result.ResultData;
import com.emre.vetproject.core.utilities.ResultGen;
import com.emre.vetproject.dto.request.doctor.DoctorSaveRequest;
import com.emre.vetproject.dto.request.doctor.DoctorUpdateRequest;
import com.emre.vetproject.dto.response.doctor.DoctorResponse;
import com.emre.vetproject.model.Doctor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public DoctorController(
            IDoctorService doctorService,
            IModelMapperService modelMapper
    ) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        this.doctorService.save(saveDoctor);

        return ResultGen.created(this.modelMapper.forResponse().map(saveDoctor, DoctorResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") int id) {
        Doctor doctor = this.doctorService.get(id);
        return ResultGen.success(this.modelMapper.forResponse().map(doctor, DoctorResponse.class));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        Doctor updateDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest, Doctor.class);
        this.doctorService.update(updateDoctor);

        return ResultGen.success(this.modelMapper.forResponse().map(updateDoctor, DoctorResponse.class));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.doctorService.delete(id);
        return ResultGen.ok();
    }

}
