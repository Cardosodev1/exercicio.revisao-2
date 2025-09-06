package br.com.fiap.exercicio.revisao.dto;

import br.com.fiap.exercicio.revisao.entity.enums.ToyClassification;
import br.com.fiap.exercicio.revisao.entity.enums.ToySize;
import br.com.fiap.exercicio.revisao.entity.enums.ToyType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ToySaveDto(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        ToyType toyType,

        @NotNull
        ToyClassification toyClassification,

        @NotNull
        ToySize toySize,

        @NotNull
        @Positive
        @Digits(integer = 5, fraction = 2)
        Double price

) {
}
