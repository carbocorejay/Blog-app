package com.blogapp.apis.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "post_title", length = 100, nullable = false)
	private String title;
	
	@Column(name = "post_content", length = 10000, nullable = false)
	private String content;
	
	
	private String imageName;
	
	
	private Date addedDate;
	
	
	//mapping of post to category and user
	@ManyToOne
	@JoinColumn(name = "category_Id")
	private Category category;
	
	@ManyToOne
	private User user;

	
	@OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
	private Set<Comment> comments= new HashSet<>();
}
