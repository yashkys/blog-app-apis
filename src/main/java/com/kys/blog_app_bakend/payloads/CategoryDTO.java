package com.kys.blog_app_bakend.payloads;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO {
        private Integer categoryId;
        @NotBlank
        @Size(min=2, message = "Category must be min of 2 characters")
        private String categoryTitle;
        @NotBlank
        @Size(max = 500, message = "Description must not be more than 500 characters")
        private String categoryDescription;

}
