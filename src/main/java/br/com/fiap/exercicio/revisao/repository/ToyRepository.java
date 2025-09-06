package br.com.fiap.exercicio.revisao.repository;

import br.com.fiap.exercicio.revisao.entity.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Long> {
}
