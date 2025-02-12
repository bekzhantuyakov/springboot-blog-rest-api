package com.springbootblog.springbootblogrestapi.service.impl;

import com.springbootblog.springbootblogrestapi.dto.CommentDto;
import com.springbootblog.springbootblogrestapi.entity.Comment;
import com.springbootblog.springbootblogrestapi.entity.Post;
import com.springbootblog.springbootblogrestapi.exception.BlogAPIException;
import com.springbootblog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springbootblog.springbootblogrestapi.repository.CommentRepository;
import com.springbootblog.springbootblogrestapi.repository.PostRepository;
import com.springbootblog.springbootblogrestapi.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;




    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment =mapToEntity((commentDto));

        //retrieve post Entity by ID
        Post post = postRepository.findById(postId).orElseThrow
                (()-> new ResourceNotFoundException("Post", "id", postId));
        //set post to comment entity
        comment.setPost(post);

        //save comment entity to the database
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        //convert list of comment entities to list of comment DTO's
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        //retrieve post Entity by ID
        Post post = postRepository.findById(postId).orElseThrow
                (()-> new ResourceNotFoundException("Post", "id", postId));

        //retrieve comment by ID
        Comment comment = commentRepository.findById(commentId).orElseThrow
                (()-> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return mapToDTO(comment);

    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        //retrieve post Entity by ID
        Post post = postRepository.findById(postId).orElseThrow
                (()-> new ResourceNotFoundException("Post", "id", postId));
        //retrieve comment by ID
        Comment comment = commentRepository.findById(commentId).orElseThrow
                (()-> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDTO(updatedComment);

    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        //retrieve post Entity by ID
        Post post = postRepository.findById(postId).orElseThrow
                (()-> new ResourceNotFoundException("Post", "id", postId));

        //retrieve comment by ID
        Comment comment = commentRepository.findById(commentId).orElseThrow
                (()-> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        commentRepository.delete(comment);



    }

    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}
