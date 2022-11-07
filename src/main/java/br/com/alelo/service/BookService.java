package br.com.alelo.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.adapter.BookAdapter;
import br.com.alelo.conf.FileSaver;
import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.domain.Book;
import br.com.alelo.enums.ExceptionsMessagesEnum;
import br.com.alelo.exception.BadRequest;
import br.com.alelo.repository.jpa.BookRepository;
import br.com.alelo.response.BookResponse;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookAdapter bookAdapter;
    
    @Autowired
	private FileSaver fileSaver;
    
    public BookResponse save(BookDTO bookDTO) {
        
        BadRequest.checkThrow( Objects.nonNull( bookRepository.findByTitle( bookDTO.getTitle() ) ),
                ExceptionsMessagesEnum.BOOK_ALREADY_REGISTERED );
        
        Book bookToSave = bookAdapter.bookDtoToEntity( bookDTO );
        
        String sumaryPath = null;
    	
        if (bookDTO.getFile() != null && !bookDTO.getFile().getOriginalFilename().isEmpty()) {
        	sumaryPath = fileSaver.write(bookDTO.getFile(), bookToSave.getUuid());
		}
    	
        bookToSave.setSumaryPath(sumaryPath);
        
        Book bookSaved = bookRepository.save( bookToSave );        
        
        return BookResponse.builder()
                .id( bookSaved.getId() )
                .build();
    }
    
    public BookResponse update(Long id, BookDTO bookDTOUpdate) {
        
        BadRequest.checkThrow( Objects.isNull( bookRepository.findById( id ) ),
                ExceptionsMessagesEnum.BOOK_NOT_FOUND );
        
        Optional<Book> oldBook = bookRepository.findById( id );
        
        Book bookUpdated = bookRepository.saveAndFlush( mountUpdate( oldBook.get(), bookDTOUpdate ) );
        
        return bookAdapter.bookEntityToDto( bookUpdated );
    }
    
    public String delete(Long id) {
        
        BadRequest.checkThrow( Objects.isNull( bookRepository.findById( id ) ),
                ExceptionsMessagesEnum.BOOK_NOT_FOUND );
        
        Optional<Book> oldBook = bookRepository.findById( id );
        
        bookRepository.deleteById(oldBook.get().getId());
        
        return "Livro deletado com sucesso!";
    }
    
    private Book mountUpdate(Book book, BookDTO bookDTOUpdate) {
        
        return Book.builder()
        .id(book.getId())
        .title( bookDTOUpdate.getTitle() )
        .author( bookDTOUpdate.getAuthor() )
        .category(bookDTOUpdate.getCategory().getCategory())
        .publishingCompany(bookDTOUpdate.getPublishingCompany())
        .build();
    }
}
