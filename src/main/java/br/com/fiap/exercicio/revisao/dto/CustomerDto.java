package br.com.fiap.exercicio.revisao.dto;

import br.com.fiap.exercicio.revisao.entity.Customer;

import java.time.LocalDateTime;

public record CustomerDto(Long id, String name, String email, String address, String phone, LocalDateTime birthDate, String cpf) {

    public CustomerDto(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhone(), customer.getBirthDate(), customer.getCpf());
    }

}
