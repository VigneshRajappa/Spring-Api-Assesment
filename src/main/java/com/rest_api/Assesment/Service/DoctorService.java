package com.rest_api.Assesment.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rest_api.Assesment.Exception.InvalidIDException;
import com.rest_api.Assesment.Model.Doctor;
import com.rest_api.Assesment.Repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;


	public Object addDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
		return ResponseEntity.ok("Doctor added...");
	}
	public Doctor findById(int docId) throws InvalidIDException {
		Optional<Doctor> doctor=doctorRepository.findById(docId);
		if(doctor==null) {
			throw new InvalidIDException("Given Doctor Id is invalid....");
		}
		return doctor.get();
	}

}
