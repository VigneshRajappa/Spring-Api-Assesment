package com.rest_api.Assesment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.Assesment.Model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
