package com.diabgnozscreenreportservice.service;

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
import org.springframework.test.context.ActiveProfiles;

import com.diabgnozscreenreportservice.domain.DiabetesRiskReport;
import com.diabgnozscreenreportservice.domain.PatientInfo;
import com.diabgnozscreenreportservice.domain.PatientRecord;
import com.diabgnozscreenreportservice.utility.PatientGender;
import com.diabgnozscreenreportservice.utility.RiskLevelEnum;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DiabetesRiskReportServiceTest {

	@InjectMocks
	private DiabetesRiskReportService diabetesRiskReportService;

	@Mock
	private RiskLevelRulesService riskLevelRulesService;

	private static PatientInfo patientInfo;
	private static List<String> patientHistory;
	private static PatientRecord patientRecord;

	@BeforeAll
	static void setUp() {
		initPatientHistory();
		initPatientInfo();
		patientRecord = new PatientRecord();
		patientRecord.setPatientHistory(patientHistory);
		patientRecord.setPatientInfo(patientInfo);
		patientRecord.setPatientAge(54);
	}

	@Test
	void buildDiabetesRiskReportTest() {
		when(riskLevelRulesService.evaluateDiabetesRiskLevelForWomen(54, patientHistory))
				.thenReturn(RiskLevelEnum.NONE);
		DiabetesRiskReport report = diabetesRiskReportService.buildDiabetesRiskReport(patientRecord);
		assertEquals(RiskLevelEnum.NONE, report.getDiabetesRiskLevel());
		
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

}
