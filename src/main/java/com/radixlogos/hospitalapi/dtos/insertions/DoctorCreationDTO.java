package com.radixlogos.hospitalapi.dtos.insertions;

import com.radixlogos.hospitalapi.enums.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DoctorCreationDTO(
        @Valid
        PersonInsertDTO personDTO,
        @NotBlank(message = "É necessário informar o documento de regitro profissional do médico!")
        String professionalRegistration,
        @NotNull(message = "É necessário informar a especialidade do médico!")
        Specialty specialty) {

}
