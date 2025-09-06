package br.com.fiap.exercicio.revisao.controller;

import br.com.fiap.exercicio.revisao.dto.SupplierDto;
import br.com.fiap.exercicio.revisao.dto.SupplierSaveDto;
import br.com.fiap.exercicio.revisao.entity.Supplier;
import br.com.fiap.exercicio.revisao.hateoas.SupplierModelAssembler;
import br.com.fiap.exercicio.revisao.repository.SupplierRepository;
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
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository repository;

    @Autowired
    private SupplierModelAssembler assembler;

    @PostMapping
    @Transactional
    public ResponseEntity<EntityModel<SupplierDto>> save(@RequestBody @Valid SupplierSaveDto supplierSaveDto, UriComponentsBuilder uriBuilder) {
        var supplier = new Supplier(supplierSaveDto);
        repository.save(supplier);
        var supplierModel = assembler.toModel(new SupplierDto(supplier));
        return ResponseEntity.created(uriBuilder.path("/suppliers/{id}").buildAndExpand(supplier.getId()).toUri()).body(supplierModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<SupplierDto>>> list(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var suppliers = repository.findAll(pageable)
                .map(SupplierDto::new)
                .map(assembler::toModel)
                .toList();
        var collectionModel = CollectionModel.of(suppliers,
                linkTo(methodOn(SupplierController.class).list(pageable)).withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EntityModel<SupplierDto>> update(@PathVariable Long id, @RequestBody @Valid SupplierDto supplierDto) {
        var supplier = repository.getReferenceById(id);
        supplier.update(supplierDto);
        return ResponseEntity.ok(assembler.toModel(new SupplierDto(supplier)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SupplierDto>> detail(@PathVariable Long id) {
        var supplier = repository.getReferenceById(id);
        return ResponseEntity.ok(assembler.toModel(new SupplierDto(supplier)));
    }

}
