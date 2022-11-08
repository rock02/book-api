package br.com.alelo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.response.BookByCategoriesResponse;
import br.com.alelo.response.BookResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags={ "Book" })
public interface BookDefinition {

    @ApiOperation(value = "getAll", nickname = "getAll", notes = "getAll books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @GetMapping
    ResponseEntity<List<BookResponse>> getAll();

    @ApiOperation(value = "create", nickname = "create", notes = "created book", consumes = "multipart/form-data")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    ResponseEntity<BookResponse> create( @Validated @ModelAttribute BookDTO bookDTO );
    
    @ApiOperation(value = "update", nickname = "update", notes = "update book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not fount"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @PatchMapping(value = "/{id}")
    ResponseEntity<BookResponse> update( @PathVariable @Validated Long id, @Validated @RequestBody @ApiParam( name = "book", required = true ) BookDTO bookDTO );

    @ApiOperation(value = "getBook", nickname = "getBook", notes = "get Book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @GetMapping(value = "/{id}")
    ResponseEntity<BookResponse> getBook(@PathVariable @Validated Long id);
    
    @ApiOperation(value = "getBook", nickname = "delete", notes = "delete Book")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Ok"),
    		@ApiResponse(code = 400, message = "Bad request"),
    		@ApiResponse(code = 500, message = "Internal server error") })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<HttpStatus> delete( @PathVariable @Validated Long id );
    
    @ApiOperation(value = "getBooksByCategories", nickname = "getBooksByCategories", notes = "getAll books by categories")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Ok"),
    		@ApiResponse(code = 400, message = "Bad request"),
    		@ApiResponse(code = 500, message = "Internal server error") })
    @GetMapping(value = "/categories")
    ResponseEntity<List<BookByCategoriesResponse>> getBooksByCategories();
}
