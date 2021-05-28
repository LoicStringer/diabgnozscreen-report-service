package com.diabgnozscreenreportservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diabgnozscreenreportservice.domain.DiabetesRiskReport;
import com.diabgnozscreenreportservice.domain.PatientRecord;
import com.diabgnozscreenreportservice.service.DiabetesRiskReportService;
import com.diabgnozscreenreportservice.service.PatientRecordService;

@RestController
@RequestMapping("/diabgnoz/report/patients")
@CrossOrigin(origins = "http://localhost:4200")
public class DiabetesRiskReportController {

	@Autowired
	private PatientRecordService patientRecordService;
	
	@Autowired
	private DiabetesRiskReportService diabetesRiskReportService;
	
	@GetMapping("/{patientId}")
	public ResponseEntity<DiabetesRiskReport> generatePatientDiabetesRiskReport(@PathVariable Long patientId) {
		PatientRecord patientRecord = patientRecordService.buildPatientRecord(patientId);
		return ResponseEntity.ok(diabetesRiskReportService.buildDiabetesRiskReport(patientRecord));
	}
	
}
