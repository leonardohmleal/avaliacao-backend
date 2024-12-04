package br.com.leonardohmleal.avaliacao_final.controller;
import br.com.leonardohmleal.avalicao_final.dto.BookDto;
import br.com.leonardohmleal.avaliacao_final.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto){
        BookDto book = service.create(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable(name = "id") long id){
        BookDto book = service.findById(id);
        this.buildSelfLink(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto){
        BookDto book = service.update(bookDto);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") long id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<BookDto>> findAll(){
        CollectionModel<BookDto> books  = CollectionModel.of(service.findAll());
        for(BookDto book : books){
            buildSelfLink(book);
        }
        this.buildCollectionLink(books);
        return new ResponseEntity<CollectionModel<BookDto>>(books, HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<BookDto>> findByName(@PathVariable(name = "name") String name){
        var books = service.findByName(name);
        return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
    }

    @GetMapping("/find/state/{state}")
    public ResponseEntity<List<BookDto>> findByState(@PathVariable(name = "state") String state){
        var books = service.findByState(state);
        return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
    }

    private void buildSelfLink(BookDto bookDto){
        //self link
        bookDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findById(bookDto.getId())
                ).withSelfRel()
        );
    }

    public void buildCollectionLink(CollectionModel<BookDto> books){
        books.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findAll()
                ).withRel(LinkRelation.of("COLLECTION"))
        );
    }


}