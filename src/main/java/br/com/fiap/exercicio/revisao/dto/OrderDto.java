package br.com.fiap.exercicio.revisao.dto;

import br.com.fiap.exercicio.revisao.entity.Order;
import br.com.fiap.exercicio.revisao.entity.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderDto(Long id, Long customerId, LocalDateTime orderDate, OrderStatus orderStatus, Double total, String address) {

    public OrderDto(Order order) {
        this(order.getId(), order.getCustomer().getId(), order.getOrderDate(), order.getOrderStatus(), order.getTotal(), order.getAddress());
    }

}
