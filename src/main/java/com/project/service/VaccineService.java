package com.project.service;
import java.util.List;

import com.project.dto.VaccineDTO;
import com.project.entity.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VaccineService {
    /**Phuc NB
     * lấy id của vắc-xin cần xuất
     **/
    Vaccine findById(Integer id);
    /**Phuc NB
     * lưu lại số lượng vắc xin sau khi xuất
     **/
    void saveVaccine(Vaccine vaccine);

    /**
     * Phuoc: Phần trang + Tìm kiếm vắc-xin
    **/
    Page<Vaccine> findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(String name, String vaccineTypeName, String origin, Pageable pageable);

    /**
     * Phuoc: Phần trang + Tìm kiếm vắc-xin
     **/
    Page<Vaccine> findAllByQuantityIsExits(String name, String vaccineType_name, String origin, Pageable pageable);

    /**
     * Phuoc: Phần trang + Tìm kiếm vắc-xin
     **/
    Page<Vaccine> findAllByQuantityIsNotExits(String name, String vaccineType_name, String origin, Pageable pageable);


    /**
     * Phuoc: Tìm kiếm vắc-xin theo ID
     **/
    VaccineDTO getVaccineById(Integer id);

    /**
     * TinVT
     * Find All VaccnineDTO
     * @return
     */
    List<VaccineDTO> getAllVaccineDTO(int index);

    /**
     * TinVT
     * Find All VaccnineDTO not pagination
     * @return
     */
    List<VaccineDTO> getAllVaccineDTONotPagination();

    /**
     * TinVT
     * Find by name and type vaccine and origin and status
     * @return
     */
    List<VaccineDTO> search(String name, String vaccineType, String origin);


    /**
     * TinVT
     * create new Vaccnine
     * @return
     */
    void createVaccine(String nameVaccine, double dosageVaccine, String licenseCode, String maintenance, String origin, String expired, String age, int quantity, int vaccineTypeId);

    /**
     * TinVT
     * Find by name Vaccnine
     * @return
     */
    Vaccine searchName(String name);

    /**
     * TinVT
     * Find by name Vaccnine
     * @return
     */
    List<Vaccine> getAllVaccine();

    Vaccine getVaccineByIdNameQuery(Integer id);

    Page<Vaccine> getAllVaccineByDuration(Pageable pageable);
}
