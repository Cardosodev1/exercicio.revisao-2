package br.com.fiap.exercicio.revisao.dto;

import br.com.fiap.exercicio.revisao.entity.Payment;
import br.com.fiap.exercicio.revisao.entity.enums.*;

public record PaymentDto(Long id, Long orderId, Double amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {

    public PaymentDto(Payment payment) {
        this(payment.getId(), payment.getOrder().getId(), payment.getAmount(), payment.getPaymentMethod(), payment.getPaymentStatus());
    }

}
