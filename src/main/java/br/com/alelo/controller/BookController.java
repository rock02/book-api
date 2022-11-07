package br.com.alelo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.adapter.BookAdapter;
import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.repository.custom.BookRepositoryImpl;
import br.com.alelo.repository.jpa.BookRepository;
import br.com.alelo.response.BookByCategoriesResponse;
import br.com.alelo.response.BookResponse;
import br.com.alelo.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController implements BookDefinition {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookAdapter bookAdapter;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookRepositoryImpl bookRepositoryImpl;
    
    @Override
    public ResponseEntity<List<BookResponse>> getAll() {
        
        return new ResponseEntity<>( bookAdapter.booksEntityToDtoForList( bookRepository.findAll() ), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<BookResponse> create( BookDTO bookDTO ) {
    	
    	
        return new ResponseEntity<>( bookService.save( bookDTO ), HttpStatus.CREATED );
    }

    @Override
    public ResponseEntity<BookResponse> update( Long id, BookDTO bookDTOUpdate ) {
        return new ResponseEntity<>( bookService.update( id, bookDTOUpdate ), HttpStatus.OK );
    }
    
    @Override
    public ResponseEntity<HttpStatus> delete( Long id ) {
    	bookService.delete( id );
    	return new ResponseEntity<>( HttpStatus.OK );
    }
    
    
    @Override
    public ResponseEntity<BookResponse> getBook( Long id) {
    	return new ResponseEntity<>( bookAdapter.bookEntityToDto( bookRepository.findById(id).orElse(null) ), HttpStatus.OK );
    }
    
    @Override
    public ResponseEntity<List<BookByCategoriesResponse>> getBooksByCategories() {
    	return new ResponseEntity<>( bookAdapter.booksEntityToCategoriesForList( bookRepositoryImpl.buscaCategorias()), HttpStatus.OK  );
    }

}
