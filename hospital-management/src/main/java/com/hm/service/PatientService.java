package com.hm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hm.entities.Patient;
import com.hm.entities.PhoneNumber;

public interface PatientService {

	public ResponseEntity<?> findPatientByName(String name);
	
	public ResponseEntity<?> savePatient(Patient patient);
	
	public List<Patient> getAllPatients();
	
	public ResponseEntity<?> updatePatient(String patientId, Patient patient);
	
	public ResponseEntity<?> viewExistingPatient(String patientId);
	
	public ResponseEntity<?> updatePhoneNumber(String patientId, PhoneNumber phoneNumber);
}
