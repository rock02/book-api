package br.com.alelo.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookByCategoriesResponse {

	private static final long serialVersionUID = 3214847945723068490L;
	
	private String category;
	private Long quantity;
}
