package com.rest_api.Assesment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest_api.Assesment.Repository.AuthRepository;


@Service
public class MyUserService implements UserDetailsService{
	
	@Autowired
	private AuthRepository authRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return authRepository.findByUsername(username);
	}

}
