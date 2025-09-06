package br.com.fiap.exercicio.revisao.controller;

import br.com.fiap.exercicio.revisao.entity.Toy;
import br.com.fiap.exercicio.revisao.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/toys-view")
public class ToyViewController {

    @Autowired
    private ToyRepository repository;

    @GetMapping
    public String list(Model model) {
        var toys = repository.findAll();
        model.addAttribute("toys", toys);
        return "toy-list";
    }

    @GetMapping("/new")
    public String newToy(Model model) {
        model.addAttribute("toy", new Toy());
        return "toy-form";
    }

    @PostMapping
    @Transactional
    public String save(@ModelAttribute Toy toy) {
        repository.save(toy);
        return "redirect:/toys-view";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        var toy = repository.findById(id).orElseThrow();
        model.addAttribute("toy", toy);
        return "toy-form";
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/toys-view";
    }

}
