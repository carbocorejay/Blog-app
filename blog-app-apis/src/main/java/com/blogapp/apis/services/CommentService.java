package com.blogapp.apis.services;

import com.blogapp.apis.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
}
