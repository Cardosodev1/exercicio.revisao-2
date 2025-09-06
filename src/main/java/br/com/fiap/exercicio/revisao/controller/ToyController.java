package br.com.fiap.exercicio.revisao.controller;

import br.com.fiap.exercicio.revisao.dto.ToyDto;
import br.com.fiap.exercicio.revisao.dto.ToySaveDto;
import br.com.fiap.exercicio.revisao.entity.Toy;
import br.com.fiap.exercicio.revisao.hateoas.ToyModelAssembler;
import br.com.fiap.exercicio.revisao.repository.ToyRepository;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/toys")
public class ToyController {

    @Autowired
    private ToyRepository repository;

    @Autowired
    private ToyModelAssembler assembler;

    @PostMapping
    @Transactional
    public ResponseEntity<EntityModel<ToyDto>> save(@RequestBody @Valid ToySaveDto toySaveDto, UriComponentsBuilder uriBuilder) {
        var toy = new Toy(toySaveDto);
        repository.save(toy);
        var toyModel = assembler.toModel(new ToyDto(toy));
        return ResponseEntity.created(uriBuilder.path("/toys/{id}").buildAndExpand(toy.getId()).toUri()).body(toyModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ToyDto>>> list(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var toys = repository.findAll(pageable)
                .map(ToyDto::new)
                .map(assembler::toModel)
                .toList();
        var collectionModel = CollectionModel.of(toys,
                linkTo(methodOn(ToyController.class).list(pageable)).withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EntityModel<ToyDto>> update(@PathVariable Long id, @RequestBody @Valid ToyDto toyDto) {
        var toy = repository.getReferenceById(id);
        toy.update(toyDto);
        return ResponseEntity.ok(assembler.toModel(new ToyDto(toy)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ToyDto>> detail(@PathVariable Long id) {
        var toy = repository.getReferenceById(id);
        return ResponseEntity.ok(assembler.toModel(new ToyDto(toy)));
    }

}
