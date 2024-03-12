package com.emre.vetproject.api;

import com.emre.vetproject.business.abstracts.IAnimalService;
import com.emre.vetproject.business.abstracts.IAppointmentService;
import com.emre.vetproject.business.abstracts.IAvailableDateService;
import com.emre.vetproject.business.abstracts.IDoctorService;
import com.emre.vetproject.core.config.modelMapper.IModelMapperService;
import com.emre.vetproject.core.result.Result;
import com.emre.vetproject.core.result.ResultData;
import com.emre.vetproject.core.utilities.ResultGen;
import com.emre.vetproject.dto.request.appointment.AppointmentDateSearchRequest;
import com.emre.vetproject.dto.request.appointment.AppointmentSaveRequest;
import com.emre.vetproject.dto.request.appointment.AppointmentUpdateRequest;
import com.emre.vetproject.dto.response.CursorResponse;
import com.emre.vetproject.dto.response.appointment.AppointmentResponse;
import com.emre.vetproject.model.Animal;
import com.emre.vetproject.model.Appointment;
import com.emre.vetproject.model.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;
    private final IAvailableDateService availableDateService;

    public AppointmentController(IAppointmentService appointmentService, IDoctorService doctorService, IAnimalService animalService, IModelMapperService modelMapper, IAvailableDateService availableDateService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.animalService = animalService;
        this.modelMapper = modelMapper;
        this.availableDateService = availableDateService;
    }

    @PostMapping("/save/{doctorId}/{animalId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@PathVariable(name = "doctorId") int doctorId, @PathVariable(name = "animalId") int animalId, @Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        Doctor doctor = doctorService.get(doctorId);
        Animal animal = animalService.get(animalId);
        availableDateService.isDateAvailable(doctor, appointmentSaveRequest);
        appointmentService.isHourAvailable(doctor, appointmentSaveRequest);
        Appointment saveAppointment = modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
        saveAppointment.setDoctor(doctor);
        saveAppointment.setAnimal(animal);
        appointmentService.save(saveAppointment);

        return ResultGen.created(modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
    }

    @PostMapping("/listbydoctorid/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> doctorCursor(
            @PathVariable(name = "doctorId") int doctorId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pagesize", required = false, defaultValue = "2") int pageSize,
            @Valid @RequestBody AppointmentDateSearchRequest appointmentDateSearchRequest) {

        LocalDateTime startHour = appointmentDateSearchRequest.getStartHour();
        LocalDateTime finishHour = appointmentDateSearchRequest.getFinishHour();
        Doctor doctor = doctorService.get(doctorId);
        Page<Appointment> appointmentPage = appointmentService.doctorCursor(doctor, startHour, finishHour, page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class));

        return ResultGen.cursor(appointmentResponsePage);
    }

    @PostMapping("/listbyanimalid/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> animalCursor(
            @PathVariable(name = "animalId") int animalId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pagesize", required = false, defaultValue = "2") int pageSize,
            @Valid @RequestBody AppointmentDateSearchRequest appointmentDateSearchRequest) {

        LocalDateTime startHour = appointmentDateSearchRequest.getStartHour();
        LocalDateTime finishHour = appointmentDateSearchRequest.getFinishHour();
        Animal animal = animalService.get(animalId);
        Page<Appointment> appointmentPage = appointmentService.animalCursor(animal, startHour, finishHour, page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class));

        return ResultGen.cursor(appointmentResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        appointmentService.delete(id);
        return ResultGen.ok();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment saveAppointment = appointmentService.get(appointmentUpdateRequest.getId());

        AppointmentSaveRequest appointmentDate = new AppointmentSaveRequest(appointmentUpdateRequest.getAppointmentDate());
        availableDateService.isDateAvailable(saveAppointment.getDoctor(), appointmentDate);
        appointmentService.isHourAvailable(saveAppointment.getDoctor(), appointmentDate);
        saveAppointment.setAppointmentDate(appointmentUpdateRequest.getAppointmentDate());
        appointmentService.save(saveAppointment);

        return ResultGen.created(modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
    }

}
