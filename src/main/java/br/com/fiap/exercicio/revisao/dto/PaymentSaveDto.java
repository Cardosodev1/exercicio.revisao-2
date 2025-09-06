package br.com.fiap.exercicio.revisao.dto;

import br.com.fiap.exercicio.revisao.entity.enums.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentSaveDto(

        @NotNull
        Long orderId,

        @NotNull
        @Positive
        @Digits(integer = 5, fraction = 2)
        Double amount,

        @NotNull
        PaymentMethod paymentMethod,

        @NotNull
        PaymentStatus paymentStatus

) {
}
