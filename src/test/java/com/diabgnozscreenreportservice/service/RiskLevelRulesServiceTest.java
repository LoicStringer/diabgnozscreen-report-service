package com.diabgnozscreenreportservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.diabgnozscreenreportservice.utility.RiskLevelEnum;

@ActiveProfiles("test")
@SpringBootTest(classes= {RiskLevelRulesService.class})
@ImportAutoConfiguration(MessageSourceAutoConfiguration.class)
class RiskLevelRulesServiceTest {
	
	@Autowired
	private RiskLevelRulesService riskLevelRulesService;
	
	private List<String> patientHistory;
	
	@BeforeEach
	void setUpForTests() {
		patientHistory=new ArrayList<String>();
	}

	@Test
	void evaluateDiabetesNoneRiskLevelTest() {
		int patientAge = 40;
		patientHistory.add("Réaction");
		assertEquals(RiskLevelEnum.NONE, riskLevelRulesService.evaluateDiabetesRiskLevelForMen(patientAge, patientHistory));
		assertEquals(RiskLevelEnum.NONE, riskLevelRulesService.evaluateDiabetesRiskLevelForWomen(patientAge, patientHistory));
	}

	@Test
	void evaluateDiabetesBorderlineRiskLevelTest() {
		int patientAge = 31;
		patientHistory.add("Microalbumine");
		patientHistory.add("Taille");
		assertEquals(RiskLevelEnum.BORDERLINE, riskLevelRulesService.evaluateDiabetesRiskLevelForMen(patientAge, patientHistory));
		assertEquals(RiskLevelEnum.BORDERLINE, riskLevelRulesService.evaluateDiabetesRiskLevelForWomen(patientAge, patientHistory));
	}
	
	@Test
	void evaluateDiabetesInDangerRiskLevelForAnyPatientAboveCriticalAgeTest() {
		int patientAge = 64;
		patientHistory.add("Microalbumine");
		patientHistory.add("Taille");
		patientHistory.add("Réaction");
		patientHistory.add("Poids");
		patientHistory.add("Fumeur");
		patientHistory.add("Anormal");
		assertEquals(RiskLevelEnum.IN_DANGER, riskLevelRulesService.evaluateDiabetesRiskLevelForMen(patientAge, patientHistory));
		assertEquals(RiskLevelEnum.IN_DANGER, riskLevelRulesService.evaluateDiabetesRiskLevelForWomen(patientAge, patientHistory));
	}
	
	@Test
	void evaluateDiabetesInDangerRiskLevelForMenUnderCriticalAgeTest() {
		int patientAge = 29;
		patientHistory.add("Microalbumine");
		patientHistory.add("Poids");
		patientHistory.add("Fumeur");
		assertEquals(RiskLevelEnum.IN_DANGER, riskLevelRulesService.evaluateDiabetesRiskLevelForMen(patientAge, patientHistory));
	}
	
	@Test
	void evaluateDiabetesInDangerRiskLevelForWomenUnderCriticalAgeTest() {
		int patientAge = 24;
		patientHistory.add("Taille");
		patientHistory.add("Réaction");
		patientHistory.add("Fumeur");
		patientHistory.add("Anormal");
		assertEquals(RiskLevelEnum.IN_DANGER, riskLevelRulesService.evaluateDiabetesRiskLevelForWomen(patientAge, patientHistory));
	}
	
	@Test
	void evaluateEarlyOnsetRiskLevelForAnyPatientAboveCriticalAgeTest() {
		int patientAge = 48;
		patientHistory.add("Microalbumine");
		patientHistory.add("Taille");
		patientHistory.add("Réaction");
		patientHistory.add("Poids");
		patientHistory.add("Fumeur");
		patientHistory.add("Anormal");
		patientHistory.add("Cholestérol");
		patientHistory.add("Vertige");
		assertEquals(RiskLevelEnum.EARLY_ONSET, riskLevelRulesService.evaluateDiabetesRiskLevelForMen(patientAge, patientHistory));
		assertEquals(RiskLevelEnum.EARLY_ONSET, riskLevelRulesService.evaluateDiabetesRiskLevelForWomen(patientAge, patientHistory));	
	}
	
	@Test
	void evaluateEarlyOnsetRiskLevelForWomenUnderCriticalAgeTest() {
		int patientAge = 18;
		patientHistory.add("Microalbumine");
		patientHistory.add("Taille");
		patientHistory.add("Réaction");
		patientHistory.add("Poids");
		patientHistory.add("Fumeur");
		patientHistory.add("Anormal");
		patientHistory.add("Cholestérol");
		patientHistory.add("Vertige");
		assertEquals(RiskLevelEnum.EARLY_ONSET, riskLevelRulesService.evaluateDiabetesRiskLevelForWomen(patientAge, patientHistory));
	}
	
	@Test
	void evaluateEarlyOnsetRiskLevelForMenUnderCriticalAgeTest() {
		int patientAge = 27;
		patientHistory.add("Microalbumine");
		patientHistory.add("Poids");
		patientHistory.add("Fumeur");
		patientHistory.add("Cholestérol");
		patientHistory.add("Vertige");
		assertEquals(RiskLevelEnum.EARLY_ONSET, riskLevelRulesService.evaluateDiabetesRiskLevelForMen(patientAge, patientHistory));
	}

}
