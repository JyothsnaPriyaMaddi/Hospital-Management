package com.hm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hm.entities.Patient;
import com.hm.repositories.PatientRepository;
import com.hm.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository repo;
	
	@Autowired
	private Patient newPatient;
	
	@Override
	public ResponseEntity<?> findPatientByName(String name) {
		Patient patient = repo.findByFirstName(name);
		if(patient == null) {
			return new ResponseEntity<>("Patient with name "+name+" not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> savePatient(Patient patient) {
	    long count = repo.count() + 1;
	    String patientId = String.format("PAT-%06d", count);
	    newPatient.setPatientId(patientId);
	    newPatient.setFirstName(patient.getFirstName());
	    newPatient.setLastName(patient.getLastName());
	    newPatient.setDateOfBirth(patient.getDateOfBirth());
	    newPatient.setGender(patient.getGender());
	    newPatient.setPhone(patient.getPhone());
	    newPatient.setEmail(patient.getEmail());
	    newPatient.setAddress(patient.getAddress());
	    newPatient.setEmergencyContactName(patient.getEmergencyContactName());
	    newPatient.setEmergencyContactPhone(patient.getEmergencyContactPhone());
	    newPatient.setStatus(patient.getStatus());
	    repo.save(newPatient);
	    return new ResponseEntity<>("Patient details are saved", HttpStatus.CREATED);
	}

	@Override
	public List<Patient> getAllPatients() {
		List<Patient> allPatients = repo.findAll();
		return allPatients;
	}

}
