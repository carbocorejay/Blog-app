package com.blogapp.apis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.apis.entities.Category;
import com.blogapp.apis.entities.Post;
import com.blogapp.apis.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category );
	
	List<Post> findByTitleContaining(String title);

}
