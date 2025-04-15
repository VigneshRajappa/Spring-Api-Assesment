package com.rest_api.Assesment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.Assesment.Model.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {

}
