package com.springbootblog.springbootblogrestapi.controller;

import com.springbootblog.springbootblogrestapi.dto.CommentDto;
import com.springbootblog.springbootblogrestapi.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(name = "CRUD REST APIs for COMMENT Controller")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;

    }
    @PostMapping("/posts/{postId}/comments")
    @Operation(summary = "Create Comment REST API",
            description = "Create Comment REST API is used to save comment into database")
    @ApiResponse(responseCode = "201",
            description = "Http Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId,
                                                    @Valid @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

    }
    @GetMapping("/posts/{postId}/comments")
    @Operation(summary = "Get All Comments REST API",
            description = "Get All Comments REST API is used to fetch all the comments from the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    @Operation(summary = "Get Comment By ID REST API",
            description = "Get Comment by ID REST API is used to get single comment from the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);

    }
    @PutMapping("/posts/{postId}/comments/{id}")
    @Operation(summary = "Update Comment REST API",
            description = "Update Comment REST API is used to update a particular comment in the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<CommentDto> updatedComment(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "id") Long commentId,
                                                     @RequestBody @Valid CommentDto commentDto){

        CommentDto updateComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);

    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    @Operation(summary = "Delete Comment REST API",
            description = "Delete Comment REST API is used to delete a particular comment in the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "id") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
