package com.rest_api.Assesment.Model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    private String role;

    
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*convert role into authority using SimpleGrantedAuthority class */
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role);
		
		/*prepare the list of GrantedAuthority and add your ur authority to it*/
		Collection<GrantedAuthority> list = new ArrayList<>();
		list.add(sga); //my authority from role. 
		
		return list;
	}

	
    
    
}

