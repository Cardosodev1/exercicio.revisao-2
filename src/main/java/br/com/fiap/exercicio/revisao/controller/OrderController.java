package br.com.fiap.exercicio.revisao.controller;

import br.com.fiap.exercicio.revisao.dto.OrderDto;
import br.com.fiap.exercicio.revisao.dto.OrderSaveDto;
import br.com.fiap.exercicio.revisao.entity.Order;
import br.com.fiap.exercicio.revisao.hateoas.OrderModelAssembler;
import br.com.fiap.exercicio.revisao.repository.OrderReposiory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderReposiory repository;

    @Autowired
    private OrderModelAssembler assembler;

    @PostMapping
    @Transactional
    public ResponseEntity<EntityModel<OrderDto>> save(@RequestBody @Valid OrderSaveDto orderSaveDto, UriComponentsBuilder uriBuilder) {
        var order = new Order(orderSaveDto);
        repository.save(order);
        var orderModel = assembler.toModel(new OrderDto(order));
        return ResponseEntity.created(uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri()).body(orderModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<OrderDto>>> list(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var orders = repository.findAll(pageable)
                .map(OrderDto::new)
                .map(assembler::toModel)
                .toList();
        var collectionModel = CollectionModel.of(orders,
                linkTo(methodOn(OrderController.class).list(pageable)).withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EntityModel<OrderDto>> update(@PathVariable Long id, @RequestBody @Valid OrderDto orderDto) {
        var order = repository.getReferenceById(id);
        order.update(orderDto);
        return ResponseEntity.ok(assembler.toModel(new OrderDto(order)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OrderDto>> detail(@PathVariable Long id) {
        var order = repository.getReferenceById(id);
        return ResponseEntity.ok(assembler.toModel(new OrderDto(order)));
    }

}
