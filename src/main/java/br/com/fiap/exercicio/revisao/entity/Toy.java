package br.com.fiap.exercicio.revisao.entity;

import br.com.fiap.exercicio.revisao.dto.ToyDto;
import br.com.fiap.exercicio.revisao.dto.ToySaveDto;
import br.com.fiap.exercicio.revisao.entity.enums.ToyClassification;
import br.com.fiap.exercicio.revisao.entity.enums.ToySize;
import br.com.fiap.exercicio.revisao.entity.enums.ToyType;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tds_tb_toys")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Toy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ToyType toyType;

    @Enumerated(EnumType.STRING)
    private ToyClassification toyClassification;

    @Enumerated(EnumType.STRING)
    private ToySize toySize;

    private Double price;

    public Toy(ToySaveDto toySaveDto) {
        this.name = toySaveDto.name();
        this.toyType = toySaveDto.toyType();
        this.toyClassification = toySaveDto.toyClassification();
        this.toySize = toySaveDto.toySize();
        this.price = toySaveDto.price();
    }

    public void update(@Valid ToyDto toyDto) {
        if (toyDto.name() != null) {
            this.name = toyDto.name();
        }
        if (toyDto.toyType() != null) {
            this.toyType = toyDto.toyType();
        }
        if (toyDto.toyClassification() != null) {
            this.toyClassification = toyDto.toyClassification();
        }
        if (toyDto.toySize() != null) {
            this.toySize = toyDto.toySize();
        }
        if (toyDto.price() != null) {
            this.price = toyDto.price();
        }
    }

}
