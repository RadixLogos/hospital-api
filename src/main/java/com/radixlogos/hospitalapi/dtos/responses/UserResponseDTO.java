package com.radixlogos.hospitalapi.dtos.responses;


import com.radixlogos.hospitalapi.entities.User;
import com.radixlogos.hospitalapi.enums.RoleType;

public record UserResponseDTO(Long id,String email, RoleType role) {
    public static UserResponseDTO fromUser(User user) {

        return new UserResponseDTO(user.getId(), user.getEmail(),user.getRole());
    }

}
