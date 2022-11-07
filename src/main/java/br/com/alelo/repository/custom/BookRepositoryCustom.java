package br.com.alelo.repository.custom;

import java.util.List;

import br.com.alelo.controller.dto.BookByCategoriesDTO;

public interface BookRepositoryCustom {

	List<BookByCategoriesDTO> buscaCategorias();

}
