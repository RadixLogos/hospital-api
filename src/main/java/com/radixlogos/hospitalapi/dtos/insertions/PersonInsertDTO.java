package com.radixlogos.hospitalapi.dtos.insertions;

import com.radixlogos.hospitalapi.entities.Address;
import com.radixlogos.hospitalapi.enums.Gender;
import com.radixlogos.hospitalapi.enums.NationalityType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PersonInsertDTO(
        @NotBlank(message = "O nome não pode estar em branco!")
        @Size(max = 32, message = "O nome pode ter apenas 32 caracteres!")
        String name,
        @NotBlank(message = "O sobrenome não pode estar em branco!")
        @Size(max = 32, message = "O sobrenome pode ter apenas 32 caracteres!")
        String lastName,
        @NotNull(message = "O gênero não pode ser nulo!")
        Gender gender,
        @CPF(message = "Cpf inválido")
        String cpf,
        @NotNull(message = "Data de nascimento é obrigatória!")
        @Past(message = "A data de nascimento deve ser no passado!")
        LocalDate birthDate,
        @Pattern(regexp = "^\\+\\d{1,3} \\(\\d{2}\\) \\d{8,9}$", message = "Telefone inválido!")
        String telephoneNumber,
        @NotBlank(message = "O número de celular é obrigatório")
        @Pattern(regexp = "^\\+\\d{1,3} \\(\\d{2}\\) \\d{8,9}$", message = "Telefone inválido!")
        String cellphoneNumber,
        @NotBlank(message = "O E-mail é obrigatório!")
        @Email(message = "Email inválido!")
        String email,
        @Valid
        @NotNull(message = "O endereço é obrigatório!")
        Address address,
        @NotNull(message = "A nacionalidade é obrigatória!")
        NationalityType nationality ) {

}
