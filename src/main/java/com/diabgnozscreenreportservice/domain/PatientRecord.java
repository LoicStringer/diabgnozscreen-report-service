package com.diabgnozscreenreportservice.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PatientRecord {

	private PatientInfo patientInfo;
	private int patientAge;
	private List<String> patientHistory;
	
	public PatientRecord() {
	}

	public PatientRecord(PatientInfo patientInfo, int patientAge, List<String> patientHistory) {
		super();
		this.patientInfo = patientInfo;
		this.patientAge = patientAge;
		this.patientHistory = patientHistory;
	}

	public PatientInfo getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public List<String> getPatientHistory() {
		return patientHistory;
	}

	public void setPatientHistory(List<String> patientHistory) {
		this.patientHistory = patientHistory;
	}

	@Override
	public String toString() {
		return "PatientRecord [patientInfo=" + patientInfo + ", patientAge=" + patientAge + ", patientHistory="
				+ patientHistory + "]";
	}
	
	
}
