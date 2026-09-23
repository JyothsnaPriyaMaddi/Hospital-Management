package com.hm.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hm.entities.Patient;
import com.hm.entities.PhoneNumber;
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
		if (patient == null) {
			return new ResponseEntity<>("Patient with name " + name + " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> savePatient(Patient patient) {

		List<Patient> allPatients = getAllPatients();
		if (!allPatients.isEmpty()) {
			Patient result = allPatients.stream().filter(p -> p.getFirstName().equals(patient.getFirstName())
					&& p.getLastName().equals(patient.getLastName())).findAny().orElse(null);
			if (result != null) {
				return new ResponseEntity<>("Patient is already registered", HttpStatus.BAD_REQUEST);
			}
		}

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

	@Override
	public ResponseEntity<?> updatePatient(String patientId, Patient patient) {
		List<Patient> allPatients = getAllPatients();
		if (!allPatients.isEmpty()) {
			Patient result = allPatients.stream().filter(p -> p.getPatientId().equals(patientId)).findAny()
					.orElse(null);
			if(result == null) {
				return new ResponseEntity<>("No Patient found with patientId: "+patientId, HttpStatus.NOT_FOUND);
			}
		}
		Patient updatePatient = allPatients.stream().filter(p -> p.getPatientId().equals(patientId)).findFirst().get();
		updatePatient.setFirstName(patient.getFirstName());
		updatePatient.setLastName(patient.getLastName());
		updatePatient.setDateOfBirth(patient.getDateOfBirth());
		updatePatient.setGender(patient.getGender());
		updatePatient.setPhone(patient.getPhone());
		updatePatient.setEmail(patient.getEmail());
		updatePatient.setAddress(patient.getAddress());
		updatePatient.setEmergencyContactName(patient.getEmergencyContactName());
		updatePatient.setEmergencyContactPhone(patient.getEmergencyContactPhone());
		updatePatient.setStatus(patient.getStatus());
		repo.save(updatePatient);
		return new ResponseEntity<>("PatientId "+patientId+"details are updated", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> viewExistingPatient(String patientId) {
		Optional<Patient> findById = repo.findById(patientId);
		if(findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK); 
		} 
		return new ResponseEntity<>("Patient with id: "+patientId+" not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> updatePhoneNumber(String patientId, PhoneNumber phoneNumber) {
		Optional<Patient> findById = repo.findById(patientId);
		if(findById.isPresent()) {
			Patient patient = findById.get();
			if(patient.getPhone() == phoneNumber.getPhone()) {
				patient.setPhone(phoneNumber.getPhone());
				repo.save(patient);
				return new ResponseEntity<>("Phone number for patientId: "+patientId+" is updated successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid Phone Number", HttpStatus.BAD_REQUEST);
			}
			
		} else {
			return new ResponseEntity<>("No patient found with patientId: "+patientId, HttpStatus.NOT_FOUND);
		}
	}

}
