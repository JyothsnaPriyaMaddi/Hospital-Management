package com.hm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hm.entities.Patient;
import com.hm.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private PatientService service;
	
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
}
