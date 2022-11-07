package br.com.alelo.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryEnum {

    HORROR("Terror"),
    COMEDY("Comédia"),
    ACTION("Ação"),
    ADVENTURE("Aventura"),
    DRAMA("Drama");

	private final String category;
	
	public static CategoryEnum fromValue( String category ) {
	    return Arrays.stream( values() )
	            .filter( f -> f.category.equals( category ) )
	            .findFirst()
	            .orElse( null );
	}
}
