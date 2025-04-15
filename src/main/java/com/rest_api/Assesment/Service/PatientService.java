package com.rest_api.Assesment.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rest_api.Assesment.Exception.InvalidIDException;
import com.rest_api.Assesment.Exception.InvalidUserNameException;
import com.rest_api.Assesment.Model.MedicalHistory;
import com.rest_api.Assesment.Model.Patient;
import com.rest_api.Assesment.Model.User;
import com.rest_api.Assesment.Repository.AuthRepository;
import com.rest_api.Assesment.Repository.MedicalHistoryRepository;
import com.rest_api.Assesment.Repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private AuthRepository authRepository;
	@Autowired
	private MedicalHistoryRepository medicalHistoryRepository; 

	@Transactional
	public Object addPatient(Patient patient, String username) throws InvalidUserNameException {
		User user=authRepository.findByUsername(username);
		if(user==null)
			throw new InvalidUserNameException("Given User Name is Invalid....");
		patient.setUser(user);
		patientRepository.save(patient);
		MedicalHistory medicalHistory=patient.getMedicalHistory();
		if(medicalHistory!=null) {
			medicalHistory.setPatient(patient);
			patient.setMedicalHistory(medicalHistory);
			
			medicalHistoryRepository.save(medicalHistory);
		}
		return ResponseEntity.ok("Patient data added...");
	}

	public Patient findById(int patId) throws InvalidIDException {
		Optional<Patient> optional=patientRepository.findById(patId);
		if(optional==null)
			throw new InvalidIDException("Gievn Patient id is Invalid...");
		return optional.get();
	}

}
