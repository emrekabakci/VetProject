package com.emre.vetproject.api;

import com.emre.vetproject.business.abstracts.IAvailableDateService;
import com.emre.vetproject.business.abstracts.IDoctorService;
import com.emre.vetproject.core.config.modelMapper.IModelMapperService;
import com.emre.vetproject.core.result.Result;
import com.emre.vetproject.core.result.ResultData;
import com.emre.vetproject.core.utilities.ResultGen;
import com.emre.vetproject.dto.request.availableDate.AvailableDateSaveRequest;
import com.emre.vetproject.dto.request.availableDate.AvailableDateUpdateRequest;
import com.emre.vetproject.dto.response.CursorResponse;
import com.emre.vetproject.dto.response.availableDate.AvailableDateResponse;
import com.emre.vetproject.model.AvailableDate;
import com.emre.vetproject.model.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/availabledate")
public class AvailableDateController {
    private final IAvailableDateService availableDateService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public AvailableDateController(IAvailableDateService availableDateService, IDoctorService doctorService, IModelMapperService modelMapper) {
        this.availableDateService = availableDateService;
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save/{doctorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@PathVariable(name = "doctorId") int doctorId, @Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        Doctor doctor = doctorService.get(doctorId);
        AvailableDate saveAvailableDate = modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        saveAvailableDate.setDoctor(doctor);
        availableDateService.save(saveAvailableDate);

        return ResultGen.created(modelMapper.forResponse().map(saveAvailableDate, AvailableDateResponse.class));
    }

    @GetMapping("/list/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @PathVariable(name = "doctorId") int doctorId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pagesize", required = false, defaultValue = "2") int pageSize) {

        Doctor doctor = doctorService.get(doctorId);
        Page<AvailableDate> availableDatePage = availableDateService.cursor(doctor, page, pageSize);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage
                .map(availableDate -> modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));

        return ResultGen.cursor(availableDateResponsePage);
    }

    @PutMapping("/update/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@PathVariable(name = "doctorId") long doctorId,
                                                    @Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        Doctor doctor = doctorService.get(doctorId);
        AvailableDate updateAvailableDate = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        updateAvailableDate.setDoctor(doctor);
        this.availableDateService.update(updateAvailableDate);

        return ResultGen.success(this.modelMapper.forResponse().map(updateAvailableDate, AvailableDateResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.availableDateService.delete(id);
        return ResultGen.ok();
    }
}
