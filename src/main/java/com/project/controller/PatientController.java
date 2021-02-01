package com.project.controller;

import com.project.dto.PatientDTO;
import com.project.entity.Patient;
import com.project.service.PatientService;
import com.project.validation.PatientByRequestDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/public/")
@CrossOrigin(origins = "*")


public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientByRequestDTOValidator patientByRequestDTOValidator;


    /**
     * Duy NP
     **/
    @GetMapping("/list")
    public ResponseEntity<Page<Patient>> getListPatient(@PageableDefault(size = 5) Pageable pageable) {
        Page<Patient> listPatient = patientService.findAllPatient2(pageable);
        return new ResponseEntity<>(listPatient, HttpStatus.OK);
    }

    /**
     * Duy NP
     **/

    @GetMapping("/r")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        return new ResponseEntity<>(this.patientService.findPatientById(id), HttpStatus.OK);
    }

    /**
     * Duy NP
     **/

    @PutMapping("/editPatient/{id}")
    public ResponseEntity<?> editPatient(@Valid @RequestBody PatientDTO patientDTO, BindingResult bindingResult, @PathVariable int id) {
        Patient patient1 = patientService.findPatientById(id);

        patientByRequestDTOValidator.validate(patientDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }

        if (patient1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            patient1.setPatientId(id);
            patient1.setName(patientDTO.getName());
            patient1.setDateOfBirth(patientDTO.getDateOfBirth());
            patient1.setGender(patientDTO.getGender());
            patient1.setGuardian(patientDTO.getGuardian());
            patient1.setPhone(patientDTO.getPhone());
            patient1.setAddress(patientDTO.getAddress());
            patient1.setEmail(patientDTO.getEmail());
            patientService.editPatient(patient1);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    /**
     * NhiTTY
     **/
//    @PostMapping(value = "/patient/create")
//    public ResponseEntity<?> createPatient(@Valid @RequestBody PatientDTO patientDTO, BindingResult bindingResult) {
//        patientByRequestDTOValidator.validate(patientDTO, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
//        }
//        patientService.addPatient(patientDTO);
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }

    /**
     * Duy NP
     **/
    @GetMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {

        Patient patient = this.patientService.findPatientById(id);
        System.out.println(patient.getPatientId());
        if (patient.getPatientId() != null) {
            patient.setDeleteFlag(true);
            this.patientService.editPatient(patient);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Duy NP
     **/
    @GetMapping("/searchFullName")
    public ResponseEntity<Page<Patient>> searchFullNamePatient(@RequestParam String name,
                                                               @RequestParam String patientId,
                                                               @RequestParam int pageable) {
        Page<Patient> patientList = patientService.search(name, patientId, pageable);
        if (patientList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }
}