package com.rest_api.Assesment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest_api.Assesment.Exception.InvalidIDException;
import com.rest_api.Assesment.Model.Doctor;
import com.rest_api.Assesment.Model.Patient;
import com.rest_api.Assesment.Model.PatientDoctor;
import com.rest_api.Assesment.Service.DoctorService;
import com.rest_api.Assesment.Service.PatientDoctorService;
import com.rest_api.Assesment.Service.PatientService;

@RestController
public class PatientDoctorController {

	@Autowired
	private PatientDoctorService patientDoctorService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@PostMapping("/api/doctor/patient/add/{docId}/{patId}")
	public Object addPatientDoctor(@PathVariable int docId,@PathVariable int patId,@RequestBody PatientDoctor patientDoctor) throws InvalidIDException {
		Doctor doctor=doctorService.findById(docId);
		Patient patient=patientService.findById(patId);
		patientDoctor.setDoctor(doctor);
		patientDoctor.setPatient(patient);
		return patientDoctorService.addPatientDoctor(patientDoctor);
	}
	@GetMapping("/api/patinet/getByDoctorId/{docId}")
	public List<Patient> getByDoctorId(@PathVariable int docId) throws InvalidIDException {
		return patientDoctorService.getByDoctorId(docId);
	}
}
