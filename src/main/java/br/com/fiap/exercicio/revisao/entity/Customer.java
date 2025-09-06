package br.com.fiap.exercicio.revisao.entity;

import br.com.fiap.exercicio.revisao.dto.CustomerDto;
import br.com.fiap.exercicio.revisao.dto.CustomerSaveDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tds_tb_customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String address;
    private String phone;
    private LocalDateTime birthDate;
    private String cpf;

    public Customer(Long customerId) {
        this.id = customerId;
    }

    public Customer(CustomerSaveDto customerSaveDto) {
        this.name = customerSaveDto.name();
        this.email = customerSaveDto.email();
        this.address = customerSaveDto.address();
        this.phone = customerSaveDto.phone();
        this.birthDate = customerSaveDto.birthDate();
        this.cpf = customerSaveDto.cpf();
    }

    public void update(@Valid CustomerDto customerDto) {
        if (customerDto.name() != null) {
            this.name = customerDto.name();
        }
        if (customerDto.email() != null) {
            this.email = customerDto.email();
        }
        if (customerDto.address() != null) {
            this.address = customerDto.address();
        }
        if (customerDto.phone() != null) {
            this.phone = customerDto.phone();
        }
        if (customerDto.birthDate() != null) {
            this.birthDate = customerDto.birthDate();
        }
        if (customerDto.cpf() != null) {
            this.cpf = customerDto.cpf();
        }
    }

}
