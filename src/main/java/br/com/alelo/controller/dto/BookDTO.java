package br.com.alelo.controller.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import br.com.alelo.enums.CategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "cannot be null")
    @Length( max = 50, message = "{invalid.title}" )
    private String title;

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 50, message = "{invalid.author}" )
    private String author;  
    
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 50, message = "{invalid.publishingCompany}" )
    private String publishingCompany;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "cannot be null")
    @ApiModelProperty(value = "category", required = true)
    private CategoryEnum category;
    
    @ApiModelProperty(value = "rating", required = false)
    private Integer rating;
    
    @ApiModelProperty(value = "file", required = false, dataType = "file")
    private MultipartFile file;
    
}
