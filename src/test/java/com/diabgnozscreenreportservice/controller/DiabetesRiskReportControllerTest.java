package com.diabgnozscreenreportservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.diabgnozscreenreportservice.domain.DiabetesRiskReport;
import com.diabgnozscreenreportservice.domain.PatientInfo;
import com.diabgnozscreenreportservice.domain.PatientRecord;
import com.diabgnozscreenreportservice.service.DiabetesRiskReportService;
import com.diabgnozscreenreportservice.service.PatientRecordService;
import com.diabgnozscreenreportservice.utility.PatientGender;
import com.diabgnozscreenreportservice.utility.RiskLevelEnum;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DiabetesRiskReportControllerTest {
	
	@InjectMocks
	private DiabetesRiskReportController diabetesRiskReportcontroller;

	@Mock
	private PatientRecordService patientRecordService;
	
	@Mock
	private DiabetesRiskReportService diabetesRiskReportService;
	
	
	private static PatientInfo patientInfo;
	private static List<String> patientHistory;
	private static PatientRecord patientRecord;
	private static DiabetesRiskReport report;

	@BeforeAll
	static void setUp() {
		initPatientHistory();
		initPatientInfo();
		initPatientRecord();
		report = new DiabetesRiskReport();
		report.setDiabetesRiskLevel(RiskLevelEnum.NONE);
		report.setPatientAge(patientRecord.getPatientAge());
		report.setPatientFirstName(patientRecord.getPatientInfo().getPatientFirstName());
		report.setPatientLastName(patientRecord.getPatientInfo().getPatientLastName());
		report.setSpeech("diabetes assessment is:");
	}
	
	@Test
	void generatePatientDiabetesRiskReportTest() {
		when(patientRecordService.buildPatientRecord(1L)).thenReturn(patientRecord);
		when(diabetesRiskReportService.buildDiabetesRiskReport(patientRecord)).thenReturn(report);
		assertEquals(ResponseEntity.ok(report),diabetesRiskReportcontroller.generatePatientDiabetesRiskReport(1L));
	}

	static void initPatientHistory() {
		patientHistory = new ArrayList<String>();
		patientHistory.add("Patient states that they are 'feeling terrific' Weight at or below recommended level");
	}

	static void initPatientInfo() {
		patientInfo = new PatientInfo();
		patientInfo.setPatientBirthDate(LocalDate.of(1966, 12, 31));
		patientInfo.setPatientFirstName("Test");
		patientInfo.setPatientLastName("TestNone");
		patientInfo.setPatientGender(PatientGender.F);
		patientInfo.setPatientId(1L);
	}

	static void initPatientRecord() {
		patientRecord = new PatientRecord();
		patientRecord.setPatientHistory(patientHistory);
		patientRecord.setPatientInfo(patientInfo);
		patientRecord.setPatientAge(54);
	}
	
}
