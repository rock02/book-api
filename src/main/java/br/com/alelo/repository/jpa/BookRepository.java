package br.com.alelo.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

    Book findByTitle( String title);
    
//    @Query(nativeQuery = true)
//    List<BookByCategoriesDTO> findCategory_count();
    
}
