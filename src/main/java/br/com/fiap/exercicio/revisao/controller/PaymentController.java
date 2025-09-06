package br.com.fiap.exercicio.revisao.controller;

import br.com.fiap.exercicio.revisao.dto.PaymentDto;
import br.com.fiap.exercicio.revisao.dto.PaymentSaveDto;
import br.com.fiap.exercicio.revisao.entity.Payment;
import br.com.fiap.exercicio.revisao.hateoas.PaymentModelAssembler;
import br.com.fiap.exercicio.revisao.repository.PaymentRepository;
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
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private PaymentModelAssembler assembler;

    @PostMapping
    @Transactional
    public ResponseEntity<EntityModel<PaymentDto>> save(@RequestBody @Valid PaymentSaveDto paymentSaveDto, UriComponentsBuilder uriBuilder) {
        var payment = new Payment(paymentSaveDto);
        repository.save(payment);
        var paymentModel = assembler.toModel(new PaymentDto(payment));
        return ResponseEntity.created(uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri()).body(paymentModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<PaymentDto>>> list(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var payments = repository.findAll(pageable)
                .map(PaymentDto::new)
                .map(assembler::toModel)
                .toList();
        var collectionModel = CollectionModel.of(payments,
                linkTo(methodOn(PaymentController.class).list(pageable)).withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EntityModel<PaymentDto>> update(@PathVariable Long id, @RequestBody @Valid PaymentDto paymentDto) {
        var payment = repository.getReferenceById(id);
        payment.update(paymentDto);
        return ResponseEntity.ok(assembler.toModel(new PaymentDto(payment)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PaymentDto>> detail(@PathVariable Long id) {
        var payment = repository.getReferenceById(id);
        return ResponseEntity.ok(assembler.toModel(new PaymentDto(payment)));
    }

}
