package com.rest_api.Assesment.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest_api.Assesment.Exception.InvalidUserNameException;
import com.rest_api.Assesment.Model.Patient;
import com.rest_api.Assesment.Service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;

	@PostMapping("add")
	public Object addPatient(@RequestBody Patient patient,Principal principal) throws InvalidUserNameException {
		String username=principal.getName();
		return patientService.addPatient(patient,username);
	}
	
}
