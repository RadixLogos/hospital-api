package com.radixlogos.hospitalapi.dtos.insertions;

import com.radixlogos.hospitalapi.enums.WorkShiftType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;

/* Será necessário criar uma PaySheetCreationDTO logo em seguida para deixa-la como parâmetro junto com Employee
 * no serive que irá criar a entidade específica
 */
public record EmployeeCreationDTO(
        @NotNull(message = "O salario é obrigatório!")
        @PositiveOrZero(message = "O salario deve ser um número positivo!")
        BigDecimal salary,
        @NotNull(message = "O turno de trabalho é obrigatório!")
        WorkShiftType workShift,
        @NotNull(message = "O departamento é obrigatório")
        Long departmentId) {

}
