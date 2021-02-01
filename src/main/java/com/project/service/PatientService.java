package com.project.service;
import com.project.dto.PatientDTO;
import com.project.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PatientService {

    /**
     * Duy NP
     **/

    Page<Patient> findAllPatient2(Pageable pageable);

    /**
     * Duy NP
     **/
    Patient findPatientById(int id);
    /**
     * Duy NP
     **/
    Page<Patient> search(String name, String id, int pageable);
    void editPatient(Patient patient);

    /**
     *NhiTTY
     **/
    void addPatient(String name, String dateOfBirth, String gender, String guardian, String phone, String address, String email,Integer accountId,Boolean deleteFlag);



    /**
     * Phuoc: tạo mới bênh nhân
     **/
    Patient create(Patient patientTemp);


    /**
     * Phuoc: Tìm kiếm theo Email
     **/
    Integer findByEmail(String email);



    /**
     * Phuoc: Tìm kiếm theo Phone
     **/
    Integer findByPhone(String phone);


    /**
     * Phuoc
     **/
    List<Patient> findAllByEmail(String email, boolean b);

    /**
     * Linh: find patient by account id
     */
    Patient findByAccountId(int id, boolean b);

    Patient getPatientById(Integer patientId);

    /**
     * Linh
     */

}
