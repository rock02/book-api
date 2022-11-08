package br.com.alelo.controller.dto;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
    
    @Nonnull
    private String title;
    @Nonnull
    private String author;  
    @Nonnull
    private String publishingCompany;
    
    @Enumerated(EnumType.STRING)
    @Nonnull
    @ApiModelProperty(value = "category", required = true)
    private CategoryEnum category;
    
    @ApiModelProperty(value = "rating", required = false)
    private Integer rating;
    
    @ApiModelProperty(value = "file", required = false, dataType = "file")
    private MultipartFile file;
    
}
