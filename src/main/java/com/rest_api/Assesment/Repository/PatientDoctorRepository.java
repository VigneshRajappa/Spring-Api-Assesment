package com.rest_api.Assesment.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.Assesment.Model.Doctor;
import com.rest_api.Assesment.Model.PatientDoctor;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer> {


	//List<PatientDoctor> getPatientDoctorById(int docId);

	List<PatientDoctor> findByDoctor(Doctor optional);

}
