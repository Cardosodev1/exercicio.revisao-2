package br.com.fiap.exercicio.revisao.dto;

import jakarta.validation.constraints.*;

public record SupplierSaveDto(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        @Email
        String email,

        @NotNull
        String address,

        @NotNull
        @NotBlank
        String cnpj,

        @NotNull
        @Pattern(regexp = "\\d{11}")
        String phone

) {
}
