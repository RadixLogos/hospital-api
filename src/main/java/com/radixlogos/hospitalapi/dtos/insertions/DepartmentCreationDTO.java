package com.radixlogos.hospitalapi.dtos.insertions;

import com.radixlogos.hospitalapi.entities.Department;
import com.radixlogos.hospitalapi.enums.DepartmentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record DepartmentCreationDTO(
        @NotNull(message = "O tipo de departamento não pode ser nulo!")
        DepartmentType departmentType,
        @PositiveOrZero(message = "O número do andar não pode ser negaativo!")
        int floorNumber,
        @PositiveOrZero(message = "O número leitos não pode ser negativo!")
        int numberOfBeds,
        @NotNull(message = "É necessário informar o hospital!")
        Long hospitalId) {

    public static List<DepartmentCreationDTO> convertSetOfDeparments(Set<Department> d) {
        List<DepartmentCreationDTO> departmentsDTO = new ArrayList<>();
        d.forEach(dep -> {
            var depDTO = convertFromDepartmentEntity(dep);
            departmentsDTO.add(depDTO);
        });
        return departmentsDTO;
    }

    public static DepartmentCreationDTO convertFromDepartmentEntity(Department d) {
        return new DepartmentCreationDTO(
                d.getDepartmentType(),
                d.getFloorNumber(),
                d.getNumberOfBeds(),
                d.getHospital().getId());
    }
}
