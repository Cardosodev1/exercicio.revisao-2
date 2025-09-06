package br.com.fiap.exercicio.revisao.controller;

import br.com.fiap.exercicio.revisao.dto.CustomerDto;
import br.com.fiap.exercicio.revisao.dto.CustomerSaveDto;
import br.com.fiap.exercicio.revisao.entity.Customer;
import br.com.fiap.exercicio.revisao.hateoas.CustomerModelAssembler;
import br.com.fiap.exercicio.revisao.repository.CustomerRepository;
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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerModelAssembler assembler;

    @PostMapping
    @Transactional
    public ResponseEntity<EntityModel<CustomerDto>> save(@RequestBody @Valid CustomerSaveDto customerSaveDto, UriComponentsBuilder uriBuilder) {
        var customer = new Customer(customerSaveDto);
        repository.save(customer);
        var customerModel = assembler.toModel(new CustomerDto(customer));
        return ResponseEntity.created(uriBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri()).body(customerModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CustomerDto>>> list(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var customers = repository.findAll(pageable)
                .map(CustomerDto::new)
                .map(assembler::toModel)
                .toList();
        var collectionModel = CollectionModel.of(customers,
                linkTo(methodOn(CustomerController.class).list(pageable)).withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EntityModel<CustomerDto>> update(@PathVariable Long id, @RequestBody @Valid CustomerDto customerDto) {
        var customer = repository.getReferenceById(id);
        customer.update(customerDto);
        return ResponseEntity.ok(assembler.toModel(new CustomerDto(customer)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDto>> detail(@PathVariable Long id) {
        var customer = repository.getReferenceById(id);
        return ResponseEntity.ok(assembler.toModel(new CustomerDto(customer)));
    }

}
