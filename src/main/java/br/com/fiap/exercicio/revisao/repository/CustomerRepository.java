package br.com.fiap.exercicio.revisao.repository;

import br.com.fiap.exercicio.revisao.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
