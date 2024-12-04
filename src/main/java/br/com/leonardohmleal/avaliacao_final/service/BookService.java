package br.com.leonardohmleal.avaliacao_final.service;

import br.com.leonardohmleal.avalicao_final.dto.BookDto;
import br.com.leonardohmleal.avaliacao_final.exception.ResourceNotFoundException;
import br.com.leonardohmleal.avaliacao_final.mapper.CustomModelMapper;
import br.com.leonardohmleal.avaliacao_final.model.BookModel;
import br.com.leonardohmleal.avaliacao_final.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public BookDto create(BookDto bookDto){
        BookModel book = CustomModelMapper.parseObject(bookDto, BookModel.class);
        return CustomModelMapper.parseObject(repository.save(book), BookDto.class);
    }

    public BookDto findById(long id){
        BookModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Livro não encontrado")
        );
        return CustomModelMapper.parseObject(found, BookDto.class);
    }

    public BookDto update(BookDto bookDto){
        BookModel found = repository.findById(bookDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Livro não encontrado"));
        found.setName(bookDto.getName());
        found.setState(bookDto.getState());
        return CustomModelMapper.parseObject(repository.save(found), BookDto.class);
    }
    public void delete(long id){
        BookModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Livro não encontrado"));
        repository.delete(found);
    }
    public List<BookDto> findAll(){
        var list = repository.findAll();
        return CustomModelMapper.parseObjectList(list, BookDto.class);
    }
    public List<BookDto> findByName(String name){
        var books = repository.findByNameContainsIgnoreCaseOrderByName(name);
        return CustomModelMapper.parseObjectList(books, BookDto.class);
    }

    public List<BookDto> findByState(String state){
        var books = repository.findByStateEqualsIgnoreCaseOrderByStateAscNameAsc(state);
        return CustomModelMapper.parseObjectList(books, BookDto.class);
    }
}