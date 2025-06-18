package com.radixlogos.hospitalapi.dtos.insertions;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;


public record PatientInsertDTO(
        Long hospitalId,
        @Valid
        PersonInsertDTO person,
        @NotEmpty(message = "A profissão eve ser informada")
        String profession,
        @NotEmpty(message = "O nome da mãe não pode estar em branco")
        String mothersName
        ) {

}

