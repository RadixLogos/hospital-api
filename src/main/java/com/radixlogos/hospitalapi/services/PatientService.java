package com.radixlogos.hospitalapi.services;


import com.radixlogos.hospitalapi.dtos.insertions.PatientInsertDTO;
import com.radixlogos.hospitalapi.dtos.responses.PatientFullResponseDTO;
import com.radixlogos.hospitalapi.dtos.responses.PatientResponseDTO;
import com.radixlogos.hospitalapi.entities.Hospital;
import com.radixlogos.hospitalapi.entities.Patient;
import com.radixlogos.hospitalapi.enums.RoleType;
import com.radixlogos.hospitalapi.repositories.HospitalRepository;
import com.radixlogos.hospitalapi.repositories.PatientRepository;
import com.radixlogos.hospitalapi.repositories.UserRepository;
import com.radixlogos.hospitalapi.services.exceptions.AlreadyExistsException;
import com.radixlogos.hospitalapi.services.exceptions.ConstraintException;
import com.radixlogos.hospitalapi.services.exceptions.NullValueException;
import com.radixlogos.hospitalapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  PatientRepository patientRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private AuditLogService auditLogService;

    @Transactional(readOnly = true)
    public Page<PatientFullResponseDTO> findAllPatients(Pageable pageable){
        auditLogService.logAction("Buscou todos os pacientes registrados");
        return patientRepository.findAll(pageable).map(PatientFullResponseDTO::fromPatient);
    }

    @Transactional(readOnly = true)
    public PatientResponseDTO findPatientById(Long id){
        var entityPatient = patientRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Paciente não encontrado"));
        auditLogService.logAction("Buscou paciente com id " + id);
        return PatientResponseDTO.fromPatient(entityPatient);
    }

    @Transactional
    public PatientResponseDTO insertPatient(Long hospitalId, Long userId, PatientInsertDTO inDTO){
        if(!hospitalRepository.existsById(hospitalId)){
            throw new ResourceNotFoundException("Hospital não encontrado");
        }
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        if(patientRepository.existsByCpf(inDTO.person().cpf())){
            throw new AlreadyExistsException("Paciente já cadastrado");
        }
        var entityHospital = hospitalRepository.getReferenceById(hospitalId);
        var entityUser = userRepository.getReferenceById(userId);
        var entityPatient = new Patient();
        copyDtoToEntity(entityPatient,inDTO,entityHospital);

        entityUser.setPerson(entityPatient);
        entityUser.setRole(RoleType.PATIENT);
        patientRepository.save(entityPatient);
        auditLogService.logAction("Inseriu novo paciente com id " + entityPatient.getId() );
        return PatientResponseDTO.fromPatient(entityPatient);
    }

    @Transactional
    public PatientResponseDTO updatePatient(Long id, PatientInsertDTO insertDTO){
        Long hospitalId = insertDTO.hospitalId();
        if(!patientRepository.existsById(id)){
            throw new ResourceNotFoundException("Paciente não encontrado");
        }
        if(hospitalId == null){
            throw new NullValueException("O id do hospital não pode ser nulo");
        }
        if(!hospitalRepository.existsById(hospitalId)){
            throw new ResourceNotFoundException("Hospital não encontrado");
        }
        var hospitalEntity = hospitalRepository.getReferenceById(hospitalId);
        var entityPatient = patientRepository.getReferenceById(id);
        copyDtoToEntity(entityPatient,insertDTO,hospitalEntity);
        entityPatient = patientRepository.save(entityPatient);
        auditLogService.logAction("Atualizou paciente com id " + id);
        return PatientResponseDTO.fromPatient(entityPatient);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado");
        }
        try {
            var patient = patientRepository.getReferenceById(id);
            var userPatient = userRepository.findByPerson(patient).get();
            userRepository.deleteById(userPatient.getId());
            auditLogService.logAction("Deletou paciente com id " + id);
        }catch (DataIntegrityViolationException e){
            throw new ConstraintException("Problema de violação referencial");
        }
    }
    private void copyDtoToEntity(Patient entityPatient, PatientInsertDTO inDTO, Hospital entityHospital){
        entityPatient.setName(inDTO.person().name());
        entityPatient.setLastName(inDTO.person().lastName());
        entityPatient.setGender(inDTO.person().gender());
        entityPatient.setCpf(inDTO.person().cpf());
        entityPatient.setBirthDate(inDTO.person().birthDate());
        entityPatient.setTelephoneNumber(inDTO.person().telephoneNumber());
        entityPatient.setCellphoneNumber(inDTO.person().cellphoneNumber());
        entityPatient.setEmail(inDTO.person().email());
        entityPatient.setAddress(inDTO.person().address());
        entityPatient.setNationality(inDTO.person().nationality());
        entityPatient.setHospital(entityHospital);
        entityPatient.setMothersName(inDTO.mothersName());
        entityPatient.setProfession(inDTO.profession());
    }

}
