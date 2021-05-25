package com.diabgnozscreenreportservice.domain;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.diabgnozscreenreportservice.utility.PatientGender;

@Component
public class PatientInfo {

	private Long patientId;
	private String patientLastName;
	private String patientFirstName;
	private PatientGender patientGender;
	private LocalDate patientBirthDate;

	public PatientInfo() {
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	
	public PatientGender getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(PatientGender patientGender) {
		this.patientGender = patientGender;
	}

	public LocalDate getPatientBirthDate() {
		return patientBirthDate;
	}

	public void setPatientBirthDate(LocalDate patientBirthDate) {
		this.patientBirthDate = patientBirthDate;
	}

}
