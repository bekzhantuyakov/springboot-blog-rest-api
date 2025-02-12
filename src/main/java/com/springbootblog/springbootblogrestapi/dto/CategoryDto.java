package com.springbootblog.springbootblogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "CategoryDTO Model Information")
public class CategoryDto {

    private Long id;
    @Schema(description = "Blog Category Name")
    private String name;
    @Schema(description = "Blog Category description")
    private String description;
}
