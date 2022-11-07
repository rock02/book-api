package br.com.alelo.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.alelo.controller.dto.BookByCategoriesDTO;
import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.domain.Book;
import br.com.alelo.response.BookByCategoriesResponse;
import br.com.alelo.response.BookResponse;

@Component
public class BookAdapter {

    public Book bookDtoToEntity( BookDTO bookDTO ) {
    	
         Book book = new Book();
         book.setAuthor( bookDTO.getAuthor() );
         book.setTitle( bookDTO.getTitle() );
         book.setPublishingCompany( bookDTO.getPublishingCompany() );
         book.setCategory( bookDTO.getCategory().getCategory() );
         book.setRating( bookDTO.getRating() );
         
         return book;
    }

    public BookResponse bookEntityToDto( Book book ) {

        return BookResponse.builder()
                .id( book.getId() )
                .title( book.getTitle() )
                .author( book.getAuthor() )
                .category( book.getCategory() )
                .publishingCompany( book.getPublishingCompany() )
                .rating( book.getRating() )
                .sumaryPath( book.getSumaryPath() )
                .build();
    }
    
    public static List<BookResponse> booksEntityToDtoForList( List<Book> books ) {

        List<BookResponse> listBooksResponse = new ArrayList<>();

        if (books == null) {
            return listBooksResponse;
        }

        books.forEach( book -> {
            BookResponse bookDto = BookResponse.builder()
                    .id( book.getId() )
                    .title( book.getTitle() )
                    .author( book.getAuthor() )
                    .publishingCompany( book.getPublishingCompany() )
                    .category( book.getCategory() )
                    .sumaryPath( book.getSumaryPath() )
                    .rating( book.getRating() )
                    .build();

            listBooksResponse.add( bookDto );
        } );

        return listBooksResponse;
    }
    
    
    public static List<BookByCategoriesResponse> booksEntityToCategoriesForList( List<BookByCategoriesDTO> listBookByCategories ) {

        List<BookByCategoriesResponse> listBookByCategoriesResponse = new ArrayList<>();

        if (listBookByCategories == null) {
            return listBookByCategoriesResponse;
        }

        listBookByCategories.forEach( book -> {
            BookByCategoriesResponse bookDto = BookByCategoriesResponse.builder()
                    .category( book.getCategory() )
                    .quantity( book.getQuantity())
                    .build();

            listBookByCategoriesResponse.add( bookDto );
        } );

        return listBookByCategoriesResponse;
    }
}
