package com.blogapp.apis.payloads;



import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blogapp.apis.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "username must be minimum of 4 characters")
	private String name;
	
	@NotEmpty
	@Email(message ="Email address is not valid!!")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 12, message = "Password must be minimum of 3 char and maximum of 12 chars!")
	private String password;
	
	@NotEmpty
	private String about;
	
	
	 private Set<RoleDto> roles = new HashSet<>();
	

}
