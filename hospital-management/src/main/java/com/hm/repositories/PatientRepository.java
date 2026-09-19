package com.hm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

	Patient findByFirstName(String firstName);
}
