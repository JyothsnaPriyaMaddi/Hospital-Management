package com.hm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hm.entities.Patient;

public interface PatientService {

	public ResponseEntity<?> findPatientByName(String name);
	
	public ResponseEntity<?> savePatient(Patient patient);
	
	public List<Patient> getAllPatients();
}
