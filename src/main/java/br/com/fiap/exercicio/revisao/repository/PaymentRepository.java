package br.com.fiap.exercicio.revisao.repository;

import br.com.fiap.exercicio.revisao.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
