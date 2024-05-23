package com.springbootblog.springbootblogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "PostResponse Model Information")
public class PostResponse {
    @Schema(description = "Blog PostResponse Content")
    private List<PostDto> content;
    @Schema(description = "Blog PostResponse Page Number")
    private int pageNo;
    @Schema(description = "Blog PostResponse Page Size")
    private int pageSize;
    @Schema(description = "Blog PostResponse Total Elements")
    private Long totalElements;
    @Schema(description = "Blog PostResponse Total Pages")
    private int totalPages;
    private boolean last;


}
