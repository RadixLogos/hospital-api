package com.radixlogos.hospitalapi.dtos.insertions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserInsertDTO(
        @NotBlank(message = "Informe o seu e-mail!")
        String email,
        @NotBlank(message = "Informe uma senha segura!!")
        @Size(min = 8, max = 64, message = "A senha deve conter pelo menos 8 caracteres!")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%¨&*()_\\-+=§])[A-Za-z\\d!@#$%¨&*()_\\-+=§]{8,64}$",
                message = "A senha deve conter pelo menos uma letra minúscula, uma maiúscula, um número e um caractere especial.")
        String password
) {

}
