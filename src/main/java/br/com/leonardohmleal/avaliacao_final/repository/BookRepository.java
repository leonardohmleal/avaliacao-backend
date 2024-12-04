package br.com.leonardohmleal.avaliacao_final.repository;

import br.com.leonardohmleal.avaliacao_final.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    public List<BookModel> findByNameContainsIgnoreCaseOrderByName(String name);

    public List<BookModel> findByStateEqualsIgnoreCaseOrderByStateAscNameAsc(String state);

}