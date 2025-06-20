package com.radixlogos.hospitalapi.services;

import com.radixlogos.hospitalapi.dtos.insertions.hospital.HospitalInsertDTO;
import com.radixlogos.hospitalapi.dtos.responses.HospitalResponseDTO;
import com.radixlogos.hospitalapi.entities.AuditLog;
import com.radixlogos.hospitalapi.entities.Hospital;
import com.radixlogos.hospitalapi.repositories.AuditLogRepository;
import com.radixlogos.hospitalapi.repositories.HospitalRepository;
import com.radixlogos.hospitalapi.services.exceptions.AlreadyExistsException;
import com.radixlogos.hospitalapi.services.exceptions.ConstraintException;
import com.radixlogos.hospitalapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private AuditLogService auditLogService;

    @Transactional(readOnly = true)
    public HospitalResponseDTO findHospitalById(Long id){
        var entityHospital = hospitalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hospital  não encontrado"));
        auditLogService.logAction("Buscou hospital com o id" + id);
        return HospitalResponseDTO.fromHospital(entityHospital);
    }

    @Transactional(readOnly = true)
    public Page<HospitalResponseDTO> findAllHospitals(Pageable pageable){
        auditLogService.logAction("Buscou todos os registros de hospitais");
        return hospitalRepository.findAll(pageable).map(HospitalResponseDTO::fromHospital);
    }

    @Transactional
    public HospitalResponseDTO insertHospital(HospitalInsertDTO insertDTO){
        if(hospitalRepository.existsByAddress_ZipCodeAndAddress_Number(insertDTO.address().getZipCode(),insertDTO.address().getNumber())){
            throw new AlreadyExistsException("Hospital já cadastrado");
        }
        var entityHospital = new Hospital();
        copyDtoToEntity(insertDTO, entityHospital);
        hospitalRepository.save(entityHospital);
        auditLogService.logAction("Cadastrou novo hospital com id " + entityHospital.getId());
        return HospitalResponseDTO.fromHospital(entityHospital);
    }

    @Transactional
    public HospitalResponseDTO updateHospital(Long id, HospitalInsertDTO insertDTO){
        if(!hospitalRepository.existsById(id)){
            throw new ResourceNotFoundException("Hospital não encontrado");
        }
        var entityHospital = hospitalRepository.getReferenceById(id);
        copyDtoToEntity(insertDTO, entityHospital);
        hospitalRepository.save(entityHospital);
        auditLogService.logAction("Atualizou hospital com o id" + id);
        return HospitalResponseDTO.fromHospital(entityHospital);
    }

    public void deleteHospital(Long id){
        if(!hospitalRepository.existsById(id)){
            throw new ResourceNotFoundException("Hospital  não encontrado");
        }
        try{
            hospitalRepository.deleteById(id);
            auditLogService.logAction("Deletou hospital com o id" + id);
        }catch(DataIntegrityViolationException e){
            throw new ConstraintException("Problema de violação referencial");
        }
    }

    private void copyDtoToEntity(HospitalInsertDTO insertDTO, Hospital entityHospital) {
        entityHospital.setName(insertDTO.name());
        entityHospital.setAddress(insertDTO.address());
        entityHospital.setTelephone(insertDTO.telephone());
        entityHospital.setConsultationPrice(insertDTO.consultationPrice());
        entityHospital.setHospitalizationPrice(insertDTO.hospitalizationPrice());
        entityHospital.setHospitalCash(insertDTO.hospitalCash());
    }
}
