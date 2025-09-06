package br.com.fiap.exercicio.revisao.entity;

import br.com.fiap.exercicio.revisao.dto.SupplierDto;
import br.com.fiap.exercicio.revisao.dto.SupplierSaveDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tds_tb_suppliers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String address;
    private String cnpj;
    private String phone;

    public Supplier(SupplierSaveDto supplierSaveDto) {
        this.name = supplierSaveDto.name();
        this.email = supplierSaveDto.email();
        this.address = supplierSaveDto.address();
        this.cnpj = supplierSaveDto.cnpj();
        this.phone = supplierSaveDto.phone();
    }

    public void update(@Valid SupplierDto supplierDto) {
        if (supplierDto.name() != null) {
            this.name = supplierDto.name();
        }
        if (supplierDto.email() != null) {
            this.email = supplierDto.email();
        }
        if (supplierDto.address() != null) {
            this.address = supplierDto.address();
        }
        if (supplierDto.cnpj() != null) {
            this.cnpj = supplierDto.cnpj();
        }
        if (supplierDto.phone() != null) {
            this.phone = supplierDto.phone();
        }
    }

}
