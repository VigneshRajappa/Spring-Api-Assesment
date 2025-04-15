package com.rest_api.Assesment.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest_api.Assesment.Model.Doctor;
import com.rest_api.Assesment.Model.User;
import com.rest_api.Assesment.Repository.AuthRepository;
import com.rest_api.Assesment.Service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AuthRepository authRepository;
	
	@PostMapping("add/{uId}")
	public Object addDoctor(@RequestBody Doctor doctor,@PathVariable int uId) {
		Optional<User> user=authRepository.findById(uId);
		doctor.setUser(user.get());
		return doctorService.addDoctor(doctor);
	}
}
