package br.com.fiap.exercicio.revisao.entity;

import br.com.fiap.exercicio.revisao.dto.PaymentDto;
import br.com.fiap.exercicio.revisao.dto.PaymentSaveDto;
import br.com.fiap.exercicio.revisao.dto.ToyDto;
import br.com.fiap.exercicio.revisao.dto.ToySaveDto;
import br.com.fiap.exercicio.revisao.entity.enums.PaymentMethod;
import br.com.fiap.exercicio.revisao.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tds_tb_payments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Payment(PaymentSaveDto paymentSaveDto) {
        this.order = new Order(paymentSaveDto.orderId());
        this.amount = paymentSaveDto.amount();
        this.paymentMethod = paymentSaveDto.paymentMethod();
        this.paymentStatus = paymentSaveDto.paymentStatus();
    }

    public void update(@Valid PaymentDto paymentDto) {
        if (paymentDto.orderId() != null) {
            this.order = new Order(paymentDto.orderId());
        }
        if (paymentDto.amount() != null) {
            this.amount = paymentDto.amount();
        }
        if (paymentDto.paymentMethod() != null) {
            this.paymentMethod = paymentDto.paymentMethod();
        }
        if (paymentDto.paymentStatus() != null) {
            this.paymentStatus = paymentDto.paymentStatus();
        }
    }

}
