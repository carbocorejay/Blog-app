package com.blogapp.apis.services;

import java.util.List;

import com.blogapp.apis.entities.Post;
import com.blogapp.apis.payloads.PostDto;
import com.blogapp.apis.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	//get all posts
      PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto  getPostById(Integer postId);
	
	//getting all posts by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//getting all posts by user
	List<PostDto> getPostByUser(Integer userId);
	
	
	//searching posts
	List<PostDto> searchPosts(String keyword);
}
