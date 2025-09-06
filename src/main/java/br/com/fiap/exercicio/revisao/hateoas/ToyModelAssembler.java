package br.com.fiap.exercicio.revisao.hateoas;

import br.com.fiap.exercicio.revisao.controller.ToyController;
import br.com.fiap.exercicio.revisao.dto.ToyDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ToyModelAssembler implements RepresentationModelAssembler<ToyDto, EntityModel<ToyDto>> {

    @Override
    public EntityModel<ToyDto> toModel(ToyDto toyDto) {
        return EntityModel.of(toyDto,
                linkTo(methodOn(ToyController.class).detail(toyDto.id())).withSelfRel(),
                linkTo(methodOn(ToyController.class).list(null)).withRel("toys"),
                linkTo(methodOn(ToyController.class).update(toyDto.id(), toyDto)).withRel("update"),
                linkTo(methodOn(ToyController.class).delete(toyDto.id())).withRel("delete")
        );
    }

}
