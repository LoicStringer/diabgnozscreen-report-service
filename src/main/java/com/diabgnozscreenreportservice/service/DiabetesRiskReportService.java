package com.diabgnozscreenreportservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.diabgnozscreenreportservice.domain.DiabetesRiskReport;
import com.diabgnozscreenreportservice.domain.PatientRecord;
import com.diabgnozscreenreportservice.utility.PatientGender;
import com.diabgnozscreenreportservice.utility.RiskLevelEnum;


@Service
public class DiabetesRiskReportService {

	@Value("${reportSpeech}")
	private String reportSpeech;
	
	@Autowired
	private RiskLevelRulesService riskLevelRulesService;
	
	public DiabetesRiskReport buildDiabetesRiskReport(PatientRecord patientRecord) {
		DiabetesRiskReport patientDiabetesRiskReport = new DiabetesRiskReport();
		patientDiabetesRiskReport.setDiabetesRiskLevel(assessPatientDiabetesRiskLevel(patientRecord));
		patientDiabetesRiskReport.setPatientAge(patientRecord.getPatientAge());
		patientDiabetesRiskReport.setSpeech(reportSpeech);
		patientDiabetesRiskReport.setPatientFirstName(patientRecord.getPatientInfo().getPatientFirstName());
		patientDiabetesRiskReport.setPatientLastName(patientRecord.getPatientInfo().getPatientLastName());
		return patientDiabetesRiskReport;
	}

	private RiskLevelEnum assessPatientDiabetesRiskLevel(PatientRecord patientRecord) {
		RiskLevelEnum result = null;
		PatientGender patientGender = patientRecord.getPatientInfo().getPatientGender();
		int patientAge = patientRecord.getPatientAge();
		List<String> patientHistory = patientRecord.getPatientHistory();	
		if (patientGender == PatientGender.F) {
			result = riskLevelRulesService.evaluateDiabetesRiskLevelForWomen(patientAge,patientHistory);
			return result;
		}

		result = riskLevelRulesService.evaluateDiabetesRiskLevelForMen(patientAge,patientHistory);
		return result;
	}

	
	
}
