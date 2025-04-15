package com.rest_api.Assesment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.Assesment.Model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
