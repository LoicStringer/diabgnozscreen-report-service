package com.diabgnozscreenreportservice.domain;

import com.diabgnozscreenreportservice.utility.RiskLevelEnum;

public class DiabetesRiskReport {

	private String patientFirstName;
	private String patientLastName;
	private int patientAge;
	private String speech;
	private RiskLevelEnum diabetesRiskLevel;
	
	public DiabetesRiskReport() {
	}
	
	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public RiskLevelEnum getDiabetesRiskLevel() {
		return diabetesRiskLevel;
	}

	public void setDiabetesRiskLevel(RiskLevelEnum diabetesRiskLevel) {
		this.diabetesRiskLevel = diabetesRiskLevel;
	}

	@Override
	public String toString() {
		return "DiabetesRiskReport [patientFirstName=" + patientFirstName + ", patientLastName=" + patientLastName
				+ ", patientAge=" + patientAge + ", speech=" + speech + ", diabetesRiskLevel=" + diabetesRiskLevel
				+ "]";
	}

}

