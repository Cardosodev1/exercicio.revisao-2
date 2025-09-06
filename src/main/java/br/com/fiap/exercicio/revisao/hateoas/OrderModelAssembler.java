package br.com.fiap.exercicio.revisao.hateoas;

import br.com.fiap.exercicio.revisao.controller.OrderController;
import br.com.fiap.exercicio.revisao.dto.OrderDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<OrderDto, EntityModel<OrderDto>> {

    @Override
    public EntityModel<OrderDto> toModel(OrderDto orderDto) {
        return EntityModel.of(orderDto,
                linkTo(methodOn(OrderController.class).detail(orderDto.id())).withSelfRel(),
                linkTo(methodOn(OrderController.class).list(null)).withRel("orders"),
                linkTo(methodOn(OrderController.class).update(orderDto.id(), orderDto)).withRel("update"),
                linkTo(methodOn(OrderController.class).delete(orderDto.id())).withRel("delete")
        );
    }

}
