package com.rest_api.Assesment.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest_api.Assesment.Exception.InvalidIDException;
import com.rest_api.Assesment.Exception.InvalidUserNameException;
import com.rest_api.Assesment.Model.User;
import com.rest_api.Assesment.Repository.AuthRepository;

@Service
public class AuthService {

	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	public User add(User user) throws InvalidUserNameException {
		User auth1=authRepository.findByUsername(user.getUsername());
		if(auth1 !=null)
			throw new InvalidUserNameException("User Name Already Exists...");
		
		if(user.getRole()==null)
			user.setRole("User_Default");
		String encodedPass=bcrypt.encode(user.getPassword());
		user.setPassword(encodedPass);
		return authRepository.save(user);
	}

	public User getById(int ownId) throws InvalidIDException {
		Optional<User> opt = authRepository.findById(ownId);
		if(opt.isEmpty())
			throw new InvalidIDException("User Not Found");
		return opt.get();
	}

	public User getByUsername(String name) {
		User auth1 = authRepository.findByUsername(name);
		return auth1;
	}
}
