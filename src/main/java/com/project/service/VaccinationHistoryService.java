package com.project.service;

import com.project.dto.*;
import com.project.entity.Patient;
import com.project.entity.Vaccination;
import com.project.entity.VaccinationHistory;
import com.project.entity.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface VaccinationHistoryService {
    /**
     * tuNH
     **/
    Page<VaccinationHistory> getAllVaccinationHistory(String vaccineName, String vaccinationDate, int patientId, Pageable pageable);

    /**
     * tuNH
     **/
    VaccinationHistoryFeedbackDTO findByIdVaccinationHistory(Integer vaccinationHistoryId);

    /**
     * tuNH
     **/
    void updateVaccinationHistory(Integer vaccinationHistoryId, VaccinationHistorySendFeedbackDTO vaccinationHistorySendFeedbackDTO);

    /**
     * tuNH
     **/
    VaccinationHistoryGetAfterStatusDTO getAfterStatusVaccinationHistory(Integer vaccinationHistoryId);

    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    VaccinationHistory registerVaccinationHistory(VaccinationHistory vaccinationHistoryTemp);

    /**
     * Made by Khanh lấy list history
     */
    List<VaccinationHistory> findAll();

     /**
     * Nguyen Van Linh: Get all email of patient to remind vaccination
     */

    List<String> getAllEmailToSend();

    /**
     * Nguyen Van Linh: Get all email of patient to remind vaccination with one more time they vaccination
     */
    List<String> getEmailToSendOfVaccinationMore();


    /** LuyenNT code
     *
     * @param name
     * @param status
     * @return
     */
    Page<VaccinationHistory> searchPeriodicVaccination(String name, Boolean status, Pageable pageable);

    /** LuyenNT code
     * @param name
     * @param pageable
     * @return
     */
    Page<VaccinationHistory> searchNoStatusPeriodicVaccination(String name,Pageable pageable);

    /** LuyenNT code
     */
    Page<VaccinationHistory> finAllPeriodicVaccination(Pageable pageable);
    /**
     list:  create by LongBP
     **/
    Page<VaccinationHistory> getAllRegisteredRequired(String name,Integer id, Pageable pageable);

    /**
     search and paging:  create by LongBP
     **/
    Page<VaccinationHistory> searchNameAndInjected(String name,Integer id, Boolean status, Pageable pageable);

    /**
     find by id:  create by LongBP
     **/
    List<VaccinationHistoryRegisteredDTO> findId(Integer id);

    /**
     Edit by id:  create by LongBP
     **/
    void updateStatusVaccinationHistory(Boolean status, String preStatus, Integer id);



    /**
     * KhoaTA
     * Cancel periodical vaccination register
     */
    void cancelRegister(int vaccinationId, int patientId);

    /**
     TuNH:  sendMailFeedbackForAdmin
     **/
    void sendMailFeedbackForAdmin(String value, String accountEmail) throws MessagingException, UnsupportedEncodingException;


    /**
     * Phuoc
     **/
    Page<VaccinationHistory> findAllByPatientId(Integer patientId, boolean b, Pageable pageable);

    /**
     * Phuoc
     **/
    Page<VaccinationHistory> findAllByPatient_PatientIdAndVaccination_Vaccine_NameContainingAndVaccination_Date(Integer patient_patientId, String vaccination_vaccine_name, String vaccination_date, Boolean b, Pageable pageable);


    /**
     * Phuoc
     **/
    Page<VaccinationHistory> findAllByPatient_PatientIdAndVaccination_Vaccine_NameContainingAndDeleteFlag(Integer patient_patientId, String vaccination_vaccine_name, Boolean deleteFlag, Pageable pageable);

    List<VaccinationHistory> findAllByVaccinationTransactionIsNull();

    Integer getAllVaccinationByDate(String date, boolean b);

    Integer getAllVaccinationByDate(String date, String time, boolean b);

    void sendMail(VaccinationByRequestDTO vaccinationByRequestDTO, Patient patientTemp, Vaccine vaccineTemp, Vaccination vaccination) throws MessagingException, UnsupportedEncodingException;
}
