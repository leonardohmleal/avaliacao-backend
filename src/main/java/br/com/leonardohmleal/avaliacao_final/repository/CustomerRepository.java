package br.com.leonardohmleal.avaliacao_final.repository;

import br.com.leonardohmleal.avaliacao_final.model.CustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    public Page<CustomerModel> findAll(Pageable pageable);

}