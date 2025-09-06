package br.com.fiap.exercicio.revisao.repository;

import br.com.fiap.exercicio.revisao.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
