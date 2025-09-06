package br.com.fiap.exercicio.revisao.hateoas;

import br.com.fiap.exercicio.revisao.controller.SupplierController;
import br.com.fiap.exercicio.revisao.dto.SupplierDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SupplierModelAssembler implements RepresentationModelAssembler<SupplierDto, EntityModel<SupplierDto>> {

    @Override
    public EntityModel<SupplierDto> toModel(SupplierDto supplierDto) {
        return EntityModel.of(supplierDto,
                linkTo(methodOn(SupplierController.class).detail(supplierDto.id())).withSelfRel(),
                linkTo(methodOn(SupplierController.class).list(null)).withRel("suppliers"),
                linkTo(methodOn(SupplierController.class).update(supplierDto.id(), supplierDto)).withRel("update"),
                linkTo(methodOn(SupplierController.class).delete(supplierDto.id())).withRel("delete")
        );
    }

}
