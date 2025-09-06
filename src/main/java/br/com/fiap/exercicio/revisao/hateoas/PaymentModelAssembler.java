package br.com.fiap.exercicio.revisao.hateoas;

import br.com.fiap.exercicio.revisao.controller.PaymentController;
import br.com.fiap.exercicio.revisao.dto.PaymentDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaymentModelAssembler implements RepresentationModelAssembler<PaymentDto, EntityModel<PaymentDto>>  {

    @Override
    public EntityModel<PaymentDto> toModel(PaymentDto paymentDto) {
        return EntityModel.of(paymentDto,
                linkTo(methodOn(PaymentController.class).detail(paymentDto.id())).withSelfRel(),
                linkTo(methodOn(PaymentController.class).list(null)).withRel("payments"),
                linkTo(methodOn(PaymentController.class).update(paymentDto.id(), paymentDto)).withRel("update"),
                linkTo(methodOn(PaymentController.class).delete(paymentDto.id())).withRel("delete")
        );
    }
}
