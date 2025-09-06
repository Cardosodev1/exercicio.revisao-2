package br.com.fiap.exercicio.revisao.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record CustomerSaveDto(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        @Email
        String email,

        @NotNull
        String address,

        @NotNull
        @Pattern(regexp = "\\d{11}")
        String phone,

        @NotNull
        @Past
        LocalDateTime birthDate,

        @NotNull
        @Pattern(regexp = "\\d{11}")
        String cpf

) {
}
