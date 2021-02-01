package com.project.service.impl;

import com.project.dto.VaccineDTO;
import com.project.entity.Vaccine;
import com.project.repository.VaccineRepository;
import com.project.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {
    /**Phuc NB
     **/
    @Autowired
    private VaccineRepository vaccineRepository;

    /**Phuc NB
     * lấy id vắc-xin cần xuất
     **/
    @Override
    public Vaccine findById(Integer id) {
        return vaccineRepository.findByVaccineId(id);
    }
    /**Phuc NB
     * lưu vắc-xin sau khi xuất
     **/
    @Override
    public void saveVaccine(Vaccine vaccine) {

    }


    /**
     * Phuoc: Phân trang + tìm kiếm vắc-xin
     **/
    @Override
    public Page<Vaccine> findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(String name, String vaccineTypeName, String origin, Pageable pageable) {
        return vaccineRepository.findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(name,vaccineTypeName,origin,pageable);
    }

    @Override
    public Page<Vaccine> findAllByQuantityIsExits(String name, String vaccineType_name, String origin, Pageable pageable) {
        return vaccineRepository.findAllByNameContainingAndVaccineType_NameContainingAndOriginContainingAndQuantityGreaterThan(name, vaccineType_name, origin, 0L, pageable);
    }

    @Override
    public Page<Vaccine> findAllByQuantityIsNotExits(String name, String vaccineType_name, String origin, Pageable pageable) {
        return vaccineRepository.findAllByNameContainingAndVaccineType_NameContainingAndOriginContainingAndQuantityLessThan(name, vaccineType_name, origin, 1L, pageable);
    }

    /**
     * Phuoc: Tìm kiếm vắc-xin theo ID
     **/
    @Override
    public VaccineDTO getVaccineById(Integer id) {
        return vaccineRepository.getVaccineById(id);
    }


    /**
     * TinVT
     * Find All Vaccnine
     * @return
     */
    @Override
    public List<VaccineDTO> getAllVaccineDTO(int index) {
        return vaccineRepository.getAllVaccineDTO(index);
    }


    /**
     * TinVT
     * Find All Vaccnine not pagination
     * @return
     */
    @Override
    public List<VaccineDTO> getAllVaccineDTONotPagination() {
        return vaccineRepository.getAllVaccineDTONotPagination();
    }

    /**
     * TinVT
     * Find by name and type vaccine and origin and status
     * @return
     */
    @Override
    public List<VaccineDTO> search(String name, String vaccineType, String origin) {
        return vaccineRepository.search(name,vaccineType,origin);
    }

    /**
     * TinVT
     * Create Vaccine
     * @return
     */
    @Override
    public void createVaccine(String nameVaccine, double dosageVaccine, String licenseCode, String maintenance, String origin, String expired, String age, int quantity, int vaccineTypeId) {
        vaccineRepository.createVaccine(nameVaccine, dosageVaccine, licenseCode, maintenance, origin, expired, age, quantity, vaccineTypeId);
    }

    /**
     * TinVT
     * search by name Vaccine
     * @return
     */
    @Override
    public Vaccine searchName(String name) {
        return vaccineRepository.searchName(name);
    }


    /**
     * TinVT
     * get all vaccine
     * @return
     */
    @Override
    public List<Vaccine> getAllVaccine() {
        return vaccineRepository.getAllVaccine();
    }

    @Override
    public Vaccine getVaccineByIdNameQuery(Integer id) {
        return vaccineRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Vaccine> getAllVaccineByDuration(Pageable pageable) {
        return vaccineRepository.findAllByDurationIsNotNull(pageable);
    }
}
