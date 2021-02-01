package com.project.controller;

import com.project.dto.*;
import com.project.payload.reponse.MessageResponse;
import com.project.payload.request.VerifyRequest;
import com.project.service.AccountService;
import com.project.service.VaccinationHistoryService;
import com.project.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/public/vaccination")
public class VaccinationController {
    @Autowired
    private VaccinationService vaccinationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private VaccinationHistoryService vaccinationHistoryService;

    /**KhoaTA
     *display list of registrable periodical vaccinations
     * Test Native Query
     */
//    @GetMapping("/register-list")
//    public ResponseEntity<List<RegistrablePeriodicalVaccinationDTO>> findAllRegistrablePeriodicalVaccination() {
//        List<RegistrablePeriodicalVaccinationDTO> registrableVaccinationList = this.vaccinationService.findAllRegistrableVaccination();
//
//        if (registrableVaccinationList.size() == 0) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(registrableVaccinationList, HttpStatus.OK);
//    }
    /**KhoaTA
     *display list of registrable periodical vaccinations
     */
    @GetMapping("/register-list/{id}")
    public ResponseEntity<RegistrablePeriodicalVaccinationDTO> findAllRegistrablePeriodicalVaccination(@PathVariable Integer id) {
        RegistrablePeriodicalVaccinationDTO registrableVaccination = this.vaccinationService.findRegistrableVaccinationById(id);
        if (registrableVaccination == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(registrableVaccination, HttpStatus.OK);
    }
    /** KhoaTA
     * Method for saving patient and register for periodical vaccination
     */
    @PostMapping("/register-patient")
    public ResponseEntity<Boolean> savePeriodicalVaccinationRegister(@Valid @RequestBody PeriodicalVaccinationRegisterDTO register, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError());
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                int idPatient = vaccinationService.saveRegister(register);
                RegistrablePeriodicalVaccinationDTO registrableVaccination = this.vaccinationService.findRegistrableVaccinationById(register.getVaccinationId());
                this.accountService.sendInfoEmail(register, registrableVaccination, idPatient);
                return new ResponseEntity<>(true, HttpStatus.CREATED);
            } catch (Exception exception) {
                return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }
    /**KhoaTA
     *get the list of all vaccine's ages
     */
    @GetMapping("/age-list")
    public ResponseEntity<List<String>> findAllVaccineAge() {
        List<String> ageList = this.vaccinationService.findAllVaccineAge();
        return new ResponseEntity<>(ageList, HttpStatus.OK);
    }

    /**KhoaTA
     *get the list of all vaccination's time
     */
    @GetMapping("/time-list")
    public ResponseEntity<List<TimeDTO>> findAllVaccinationTime() {
        List<TimeDTO> timeList = this.vaccinationService.findAllVaccinationTime();
        return new ResponseEntity<>(timeList, HttpStatus.OK);
    }
    /**KhoaTA
     *get the total page of search
     */
    @PostMapping("/get-total-page")
    public ResponseEntity<Integer> findTotalPage(@RequestBody PeriodicalSearchDataDTO searchData) {
        return new ResponseEntity<>((int) this.vaccinationService.getTotalPage(searchData), HttpStatus.OK);
    }
    /**KhoaTA
     *get the search periodical vaccination result
     */
    @PostMapping("/get-custom-list")
    public ResponseEntity<List<RegistrablePeriodicalVaccinationDTO>> findCustomList(@RequestBody PeriodicalSearchDataDTO searchData) {
        List<RegistrablePeriodicalVaccinationDTO> registrableVaccinationList = this.vaccinationService.findCustomVaccination(searchData);
        if (registrableVaccinationList.size() == 0) {
            return new ResponseEntity<>(registrableVaccinationList,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrableVaccinationList, HttpStatus.OK);
    }
    /**
    * KhoaTA
    */
    @PostMapping("/cancel")
    public void VerifyCancel(@RequestBody VerifyRequest code) {
        System.out.println(code.getCode());
        int vaccinationId = Integer.parseInt(code.getCode().substring(0,code.getCode().indexOf("|")));
        System.out.println(vaccinationId);
        int patientId = Integer.parseInt(code.getCode().substring(code.getCode().indexOf("|")+1));
        System.out.println(patientId);
        this.vaccinationHistoryService.cancelRegister(vaccinationId, patientId);
    }

    /** KhoaTA
     * Method for saving patient and register for periodical vaccination
     */
    @PostMapping("/check-register")
    public ResponseEntity<PeriodicalVaccinationTempRegisterDTO> checkPeriodicalVaccinationRegister(@RequestBody PeriodicalVaccinationTempRegisterDTO register) {
            return new ResponseEntity<PeriodicalVaccinationTempRegisterDTO>(this.vaccinationService.checkRegister(register), HttpStatus.OK);
    }
}
