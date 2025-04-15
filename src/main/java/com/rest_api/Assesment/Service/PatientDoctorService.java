package com.rest_api.Assesment.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rest_api.Assesment.Exception.InvalidIDException;
import com.rest_api.Assesment.Model.Doctor;
import com.rest_api.Assesment.Model.Patient;
import com.rest_api.Assesment.Model.PatientDoctor;
import com.rest_api.Assesment.Repository.PatientDoctorRepository;

@Service
public class PatientDoctorService {

	@Autowired
	private PatientDoctorRepository patientDoctorRepository;
	
	
	public List<PatientDoctor> getByDoctorId(int docId) throws InvalidIDException {
		List<PatientDoctor> list=patientDoctorRepository.getPatientDoctorById(docId);
		if(list.isEmpty())
			throw new InvalidIDException("Given Doctor id is Invalid....");
		return list;
	}


	public Object addPatientDoctor(PatientDoctor patientDoctor) {
		patientDoctorRepository.save(patientDoctor);
		return ResponseEntity.ok("Added Details...");
	}

}
