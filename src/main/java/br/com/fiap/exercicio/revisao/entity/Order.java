package br.com.fiap.exercicio.revisao.entity;

import br.com.fiap.exercicio.revisao.dto.OrderDto;
import br.com.fiap.exercicio.revisao.dto.OrderSaveDto;
import br.com.fiap.exercicio.revisao.entity.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tds_tb_orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double total;

    private String address;

    public Order(Long orderId) {
        this.id = orderId;
    }

    public Order(OrderSaveDto orderSaveDto) {
        this.customer = new Customer(orderSaveDto.customerId());
        this.orderDate = orderSaveDto.orderDate();
        this.orderStatus = orderSaveDto.orderStatus();
        this.total = orderSaveDto.total();
        this.address = orderSaveDto.address();
    }

    public void update(@Valid OrderDto orderDto) {
        if (orderDto.customerId() != null) {
            this.customer = new Customer(orderDto.customerId());
        }
        if (orderDto.orderDate() != null) {
            this.orderDate = orderDto.orderDate();
        }
        if (orderDto.orderStatus() != null) {
            this.orderStatus = orderDto.orderStatus();
        }
        if (orderDto.total() != null) {
            this.total = orderDto.total();
        }
    }

}
