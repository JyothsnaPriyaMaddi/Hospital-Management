package com.hm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hm.entities.Patient;
import com.hm.entities.PhoneNumber;
import com.hm.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private PatientService service;
	
	@GetMapping("/view/{patientId}")
	public ResponseEntity<?> viewExistingPatient(@PathVariable("patientId") String patientId) {
		ResponseEntity<?> patient = service.viewExistingPatient(patientId);
		return patient;
	}
	
	@PatchMapping("/updatePhoneNumber/{patientId}")
	public ResponseEntity<?> updatePhoneNumber(@PathVariable("patientId") String patientId, @RequestBody PhoneNumber phone) {
		ResponseEntity<?> updatePhoneNumber = service.updatePhoneNumber(patientId, phone);
		return updatePhoneNumber;
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> findPatientByName(@RequestParam("firstName") String name) {
		ResponseEntity<?> patient = service.findPatientByName(name);
		return patient;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> createNewPatient(@RequestBody Patient patient) {
		ResponseEntity<?> savePatient = service.savePatient(patient);
		return savePatient;
	}
	
	@GetMapping("/allPatients")
	public List<Patient> getAllPatients() {
		List<Patient> allPatients = service.getAllPatients();
		return allPatients;
	}
	
	@PutMapping("/update/{patientId}")
	public ResponseEntity<?> updatePatient(@PathVariable("patientId") String patientId, @RequestBody Patient patient) {
		ResponseEntity<?> updatePatient = service.updatePatient(patientId, patient);
		return updatePatient;
	}
}
