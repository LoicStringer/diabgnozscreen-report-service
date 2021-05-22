package com.diabgnozscreenreportservice.dto;

import org.springframework.beans.factory.annotation.Value;

import com.diabgnozscreenreportservice.domain.PatientInfo;
import com.diabgnozscreenreportservice.utility.RiskLevelEnum;

public class DiabetesRiskReport {

	private PatientInfo patientInfo;
	
	@Value("${reportSpeech}")
	private String speech;
	
	private RiskLevelEnum diabetesRiskLevel;
	
	public DiabetesRiskReport() {
	}

	public PatientInfo getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
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
		return "DiabetesRiskReport [patientInfo=" + patientInfo + ", speech=" + speech + ", diabetesRiskLevel="
				+ diabetesRiskLevel + "]";
	}
	
	
	
	
}

