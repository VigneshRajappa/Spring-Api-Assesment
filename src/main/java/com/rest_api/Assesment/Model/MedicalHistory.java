package com.rest_api.Assesment.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class MedicalHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String illness;
	private int NumberOfYears;
	@Column(length = 1000)
	private String CurrentMedication;
	@OneToOne
	private Patient patient;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
	public int getNumberOfYears() {
		return NumberOfYears;
	}
	public void setNumberOfYears(int numberOfYears) {
		NumberOfYears = numberOfYears;
	}
	public String getCurrentMedication() {
		return CurrentMedication;
	}
	public void setCurrentMedication(String currentMedication) {
		CurrentMedication = currentMedication;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
}
