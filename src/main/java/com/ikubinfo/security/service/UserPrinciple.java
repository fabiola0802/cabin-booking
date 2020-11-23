package com.ikubinfo.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ikubinfo.entities.UserEntity;



public class UserPrinciple  implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;
   
    private String surname;

    private String username;

    private String email;
    
    @JsonIgnore
    private String password;
   
    private Collection<? extends GrantedAuthority> authorities;
	
	   public static UserPrinciple build(UserEntity user) {
		   UserPrinciple userPrinciple = new UserPrinciple();
	        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));
	        userPrinciple.setAuthorities(grantedAuthorities);
	        userPrinciple.setId(user.getId());
	        userPrinciple.setName(user.getName());
	        userPrinciple.setSurname(user.getSurname());
	        userPrinciple.setUsername(user.getUsername());
	        userPrinciple.setEmail(user.getEmail());
	        userPrinciple.setPassword(user.getPassword());

	        return userPrinciple;
	   }
	   
	   

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
