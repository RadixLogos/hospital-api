package com.radixlogos.hospitalapi.dtos.insertions.hospital;


import com.radixlogos.hospitalapi.dtos.insertions.DepartmentCreationDTO;
import com.radixlogos.hospitalapi.entities.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record HospitalInsertDTO(
        @NotEmpty
        String name,
        @Valid
        Address address,
        @Pattern(regexp = "^\\+\\d{1,3} \\(\\d{2}\\) \\d{8,9}$", message = "Telefone inválido!")
        @NotEmpty
        String telephone,
        @NotNull(message = "O valor da consulta não pode ser nulo!")
        @PositiveOrZero(message = "O valor da consulta deve ser positivo ou zero")
        @Digits(integer = 10, fraction = 2, message = "O valor da consulta deve ter no máximo 10 dígitos inteiros e 2 decimais")
        BigDecimal consultationPrice,
        @NotNull(message = "O valor da internação não pode ser nulo!")
        @PositiveOrZero(message = "O valor da internação deve ser positivo ou zero")
        @Digits(integer = 10, fraction = 2, message = "O valor da internação deve ter no máximo 10 dígitos inteiros e 2 decimais")
        BigDecimal hospitalizationPrice,
        @NotNull(message = "O valor do caixa não pode ser nulo!")
        @PositiveOrZero(message = "O valor do caixa deve ser positivo ou zero")
        @Digits(integer = 10, fraction = 2, message = "O valor do caixa deve ter no máximo 10 dígitos inteiros e 2 decimais")
        BigDecimal hospitalCash
) {

}
