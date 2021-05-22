package com.diabgnozscreenreportservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diabgnozscreenreportservice.domain.PatientHistoryContent;
import com.diabgnozscreenreportservice.proxy.NotesServiceProxy;

@RestController
@RequestMapping("/diabgnoz/report/patients")
public class ReportController {

	@Autowired
	private NotesServiceProxy nProxy;
	
	
	/*
	@GetMapping("/{patientId}")
	public DiabetesRiskReport getPatientDiabetesRiskReport(@PathVariable Long patientId) {
		DiabetesRiskReport report = new DiabetesRiskReport();
		return report;
	}
	*/
	
	@GetMapping("/{patientId}")
	public PatientHistoryContent test (@PathVariable Long patientId) {
		return new PatientHistoryContent(nProxy.getPatientHistory(patientId));
	}
}
