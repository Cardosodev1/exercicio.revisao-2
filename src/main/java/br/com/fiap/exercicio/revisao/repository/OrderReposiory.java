package br.com.fiap.exercicio.revisao.repository;

import br.com.fiap.exercicio.revisao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReposiory extends JpaRepository<Order, Long> {
}
