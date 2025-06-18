package com.radixlogos.hospitalapi.dtos.insertions.hospital;


import com.radixlogos.hospitalapi.entities.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record HospitalSystemCreationDTO(
        @NotBlank(message = "O nome do hospital não pode estar em branco")
        @Size(max = 32, message = "O nome do hospital deve conter no máximo 32 caracteres")
        String name,
        @Valid
        @NotNull(message = "O endereço é obrigatório!")
        Address address,
        @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}", message = "Telefone inválido! Use o formato (XX)XXXXX-XXXX")
        @NotBlank(message = "O telefone não pode estar em branco!")
        String telephone
) {
}
