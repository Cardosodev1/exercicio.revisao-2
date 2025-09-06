package br.com.fiap.exercicio.revisao.dto;

import br.com.fiap.exercicio.revisao.entity.Supplier;

public record SupplierDto(Long id, String name, String email, String address, String cnpj, String phone) {

    public SupplierDto(Supplier supplier) {
        this(supplier.getId(), supplier.getName(), supplier.getEmail(), supplier.getAddress(), supplier.getCnpj(), supplier.getPhone());
    }

}
