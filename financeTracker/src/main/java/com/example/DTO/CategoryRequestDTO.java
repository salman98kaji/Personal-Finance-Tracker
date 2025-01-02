package com.example.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank
    private String categoryName;

    @NotBlank
    private String categoryType;
}
