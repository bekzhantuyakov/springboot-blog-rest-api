package com.springbootblog.springbootblogrestapi.controller;

import com.springbootblog.springbootblogrestapi.dto.PostDto;
import com.springbootblog.springbootblogrestapi.dto.PostResponse;
import com.springbootblog.springbootblogrestapi.service.PostService;
import com.springbootblog.springbootblogrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "CRUD REST APIs for POST Controller")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post REST API
    @Operation(summary = "Get Post REST API",
    description = "Get Post REST API is used to save post into database")
    @ApiResponse(responseCode = "201",
    description = "Http Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all posts REST API
    @Operation(summary = "Get All Posts REST API",
            description = "Get All Posts REST API is used to fetch all posts from the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false)int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false)String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false)String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //get post by id
    @Operation(summary = "Create Post By ID REST API",
            description = "Create Post by ID REST API is used to get single post from the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }

    //update post by ID REST API
    @Operation(summary = "Update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
       PostDto postResponse = postService.updatePost(postDto,id);
       return new ResponseEntity<>(postResponse, HttpStatus.OK);

    }

    //delete post RES API
    @Operation(summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post in the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id")Long id){

        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);

    }

    //Build Get Posts By Category REST API
    //http://localhost:8080/api/posts/category/3
    @GetMapping("/category/{id}")
    public  ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId){
    List<PostDto> postDto = postService.getPostsByCategory(categoryId);
    return ResponseEntity.ok(postDto);
    }
}
