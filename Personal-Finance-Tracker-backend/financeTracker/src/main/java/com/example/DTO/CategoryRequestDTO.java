package com.example.DTO;

import com.example.entities.Enums;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank
    private String categoryName;

    @NotBlank
    @Schema(description = "The type of the category")
    private Enums.CategoryType categoryType;
}
