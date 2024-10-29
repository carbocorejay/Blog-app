package com.blogapp.apis.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	
	private Integer  categoryId;
	
	@NotBlank
	@Size(min = 4, message ="Min size of title should be 4 ")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 4,message ="Min size of Description should be 4 ")
	private String categoryDescription;
	
}
