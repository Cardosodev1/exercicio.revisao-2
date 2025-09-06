package br.com.fiap.exercicio.revisao.dto;

import br.com.fiap.exercicio.revisao.entity.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record OrderSaveDto(

        @NotNull
        Long customerId,

        @NotNull
        LocalDateTime orderDate,

        @NotNull
        OrderStatus orderStatus,

        @NotNull
        @Positive
        Double total,

        @NotNull
        String address

) {
}
