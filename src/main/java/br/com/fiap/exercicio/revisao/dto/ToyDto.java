package br.com.fiap.exercicio.revisao.dto;

import br.com.fiap.exercicio.revisao.entity.Toy;
import br.com.fiap.exercicio.revisao.entity.enums.ToyClassification;
import br.com.fiap.exercicio.revisao.entity.enums.ToySize;
import br.com.fiap.exercicio.revisao.entity.enums.ToyType;

public record ToyDto(Long id, String name, ToyType toyType, ToyClassification toyClassification, ToySize toySize, Double price) {

    public ToyDto(Toy toy) {
        this(toy.getId(), toy.getName(), toy.getToyType(), toy.getToyClassification(), toy.getToySize(), toy.getPrice());
    }

}
