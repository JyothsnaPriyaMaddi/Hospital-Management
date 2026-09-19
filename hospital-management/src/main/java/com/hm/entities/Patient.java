package com.hm.entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Component
@Table(name = "Patient")
public class Patient {

	@Id
	@Column(name="patient_id")
	private String patientId;
	
	@Column(name="first_Name")
	private String firstName;
	
	@Column(name="last_Name")
	private String lastName;
	
	@Column(name = "date_Of_Birth")
	private String dateOfBirth;
	
	private char gender;
	
	private long phone;
	
	private String email;
	
	private String address;
	
	@Column(name = "emergency_Contact_Name")
	private String emergencyContactName;
	
	@Column(name = "emergency_Contact_Phone")
	
	private String emergencyContactPhone;
	
	private String status;
	
}
