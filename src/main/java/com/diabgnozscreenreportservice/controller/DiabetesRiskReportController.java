package com.diabgnozscreenreportservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
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
public class DiabetesRiskReportController {

	@Autowired
    ResourceBundleMessageSource messageSource;
	
	@Autowired
	private PatientRecordService patientRecordService;
	
	@Autowired
	private DiabetesRiskReportService diabetesRiskReportService;
	
	@GetMapping("/{patientId}")
	public DiabetesRiskReport generatePatientDiabetesRiskReport(@PathVariable Long patientId) {
		PatientRecord patientRecord = patientRecordService.buildPatientRecord(patientId);
		return diabetesRiskReportService.buildDiabetesRiskReport(patientRecord);
	}
	
	
	@GetMapping("/test")
	public String test () {
		return messageSource.getMessage("keywords.list",null,LocaleContextHolder.getLocale());
	}
}
