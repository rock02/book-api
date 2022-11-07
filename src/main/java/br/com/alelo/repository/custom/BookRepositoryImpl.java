package br.com.alelo.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.alelo.controller.dto.BookByCategoriesDTO;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BookByCategoriesDTO> buscaCategorias() {

		StringBuilder sql = new StringBuilder();
			sql.append(" SELECT new br.com.alelo.controller.dto.BookByCategoriesDTO( b.category, count(b.id))")
				.append(" FROM Book AS b")
				.append(" GROUP BY b.category");

		List<BookByCategoriesDTO> list = entityManager.createQuery(sql.toString(), BookByCategoriesDTO.class)
				.getResultList();

		return list;
	}

}
