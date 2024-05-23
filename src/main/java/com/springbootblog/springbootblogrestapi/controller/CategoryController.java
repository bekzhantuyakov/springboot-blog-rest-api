package com.springbootblog.springbootblogrestapi.controller;


import com.springbootblog.springbootblogrestapi.dto.CategoryDto;
import com.springbootblog.springbootblogrestapi.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "CRUD REST APIs for CATEGORY Controller")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //Build Add Category REST API
    @Operation(summary = "Create Category REST API",
            description = "Create Category REST API is used to save category into database")
    @ApiResponse(responseCode = "201",
            description = "Http Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);

    }

    //Build Get Category REST API
    @GetMapping("{id}")
    @Operation(summary = "Get Category By ID REST API",
            description = "Get Category by ID REST API is used to get single category from the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long getCategoryId){
        CategoryDto categoryDto = categoryService.getCategory(getCategoryId);
        return ResponseEntity.ok(categoryDto);
    }

    //Build Get All Category REST API
    @GetMapping
    @Operation(summary = "Get All Category REST API",
            description = "Get All Category REST API is used to fetch all the category from the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //Build Update Category REST API
    @Operation(summary = "Update Category REST API",
            description = "Update Category REST API is used to update a particular category in the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
    }

    //Build Delete Category REST API
    @PreAuthorize(("hasRole('ADMIN')"))
    @DeleteMapping("{id}")
    @Operation(summary = "Delete Category REST API",
            description = "Delete Category REST API is used to delete a particular category in the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully!");
    }

}
