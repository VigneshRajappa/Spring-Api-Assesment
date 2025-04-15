package com.rest_api.Assesment.Service;

import java.util.ArrayList;
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
	@Autowired
	private DoctorService doctorService;
	
	//fetching the patients by doctor id
	public List<Patient> getByDoctorId(int docId) throws InvalidIDException {
		//first finding the details by its id
		//store it in a variable
		//patient and doctor will be in many to many relationship
		Doctor optional=doctorService.findById(docId);
		//here we are getting the patient data which are in the doctor
		List<PatientDoctor> list=patientDoctorRepository.findByDoctor(optional);
		if(list.isEmpty())
			throw new InvalidIDException("Given Doctor id is Invalid....");
		//storing the list for patient
		List<Patient> patients = new ArrayList<>();
		list.forEach(dp -> patients.add(dp.getPatient()));
		return patients;

	}


	public Object addPatientDoctor(PatientDoctor patientDoctor) {
		patientDoctorRepository.save(patientDoctor);
		return ResponseEntity.ok("Added Details...");
	}

}
