package br.com.fiap.exercicio.revisao.hateoas;

import br.com.fiap.exercicio.revisao.controller.CustomerController;
import br.com.fiap.exercicio.revisao.dto.CustomerDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<CustomerDto, EntityModel<CustomerDto>> {

    @Override
    public EntityModel<CustomerDto> toModel(CustomerDto customerDto) {
        return EntityModel.of(customerDto,
                linkTo(methodOn(CustomerController.class).detail(customerDto.id())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).list(null)).withRel("customers"),
                linkTo(methodOn(CustomerController.class).update(customerDto.id(), customerDto)).withRel("update"),
                linkTo(methodOn(CustomerController.class).delete(customerDto.id())).withRel("delete")
        );
    }

}
