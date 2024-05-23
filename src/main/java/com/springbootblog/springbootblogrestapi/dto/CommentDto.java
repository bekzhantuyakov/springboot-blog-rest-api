package com.springbootblog.springbootblogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "CommentDTO Model Information")
public class CommentDto {
    private Long id;
    //name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    @Schema(description = "Blog Comment Name")
    private String name;

    //email should not be null or empty
    //email should not be null or empty
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    @Schema(description = "Blog Comment Email")
    private String email;

    //comment body should not be null or empty
    //comment body must be minimum 19 characters
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 19 characters")
    @Schema(description = "Blog Comment Body")
    private String body;
}
