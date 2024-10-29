package com.blogapp.apis.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.apis.entities.Comment;
import com.blogapp.apis.entities.Post;
import com.blogapp.apis.exceptions.ResourceNotFoundException;
import com.blogapp.apis.payloads.CommentDto;
import com.blogapp.apis.repositories.CommentRepo;
import com.blogapp.apis.repositories.PostRepo;
import com.blogapp.apis.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

          Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		  
          Comment comment = this.modelMapper.map(commentDto, Comment.class);
		  
          comment.setPost(post);
          
          Comment savedComment = this.commentRepo.save(comment);
          
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
	
		this.commentRepo.delete(com);
	}

}
