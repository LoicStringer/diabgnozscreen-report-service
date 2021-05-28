package com.diabgnozscreenreportservice.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.diabgnozscreenreportservice.domain.NoteContent;
import com.diabgnozscreenreportservice.domain.PatientInfo;
import com.diabgnozscreenreportservice.proxy.NotesServiceProxy;
import com.diabgnozscreenreportservice.proxy.PatientServiceProxy;
import com.diabgnozscreenreportservice.utility.PatientGender;

@SpringBootTest
@AutoConfigureMockMvc
class DiabetesRiskReportOperationsTestIT {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientServiceProxy patientServiceProxy;

	@MockBean
	private NotesServiceProxy notesServiceProxy;

	private static PatientInfo patientInfoNone;
	private static List<NoteContent> patientHistoryNone;
	private static PatientInfo patientInfoBorderline;
	private static List<NoteContent> patientHistoryBorderline;
	private static PatientInfo patientInfoInDanger;
	private static List<NoteContent> patientHistoryInDanger;
	private static PatientInfo patientInfoEarlyOnset;
	private static List<NoteContent> patientHistoryEarlyOnset;

	@BeforeAll
	static void setUp() {
		initPatientsHistory();
		initPatientsInfo();
	}

	@Test
	void isNoneRiskPatientWellEvaluatedTest() throws Exception {
		when(patientServiceProxy.getPatientInfo(1L)).thenReturn(patientInfoNone);
		when(notesServiceProxy.getPatientHistory(1L)).thenReturn(patientHistoryNone);

		mockMvc.perform(get("/diabgnoz/report/patients/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.diabetesRiskLevel").value("NONE"));

	}

	@Test
	void isBorderlineRiskPatientWellEvaluatedTest() throws Exception {
		when(patientServiceProxy.getPatientInfo(2L)).thenReturn(patientInfoBorderline);
		when(notesServiceProxy.getPatientHistory(2L)).thenReturn(patientHistoryBorderline);

		mockMvc.perform(get("/diabgnoz/report/patients/2")).andExpect(status().isOk())
				.andExpect(jsonPath("$.diabetesRiskLevel").value("BORDERLINE"));

	}

	@Test
	void isInDangerRiskPatientWellEvaluatedTest() throws Exception {
		when(patientServiceProxy.getPatientInfo(3L)).thenReturn(patientInfoInDanger);
		when(notesServiceProxy.getPatientHistory(3L)).thenReturn(patientHistoryInDanger);

		mockMvc.perform(get("/diabgnoz/report/patients/3")).andExpect(status().isOk())
				.andExpect(jsonPath("$.diabetesRiskLevel").value("IN_DANGER"));

	}

	@Test
	void isEarlyOnsetRiskPatientWellEvaluatedTest() throws Exception {
		when(patientServiceProxy.getPatientInfo(4L)).thenReturn(patientInfoEarlyOnset);
		when(notesServiceProxy.getPatientHistory(4L)).thenReturn(patientHistoryEarlyOnset);

		mockMvc.perform(get("/diabgnoz/report/patients/4")).andExpect(status().isOk())
				.andExpect(jsonPath("$.diabetesRiskLevel").value("EARLY_ONSET"));

	}

	static void initPatientsHistory() {
		patientHistoryNone = new ArrayList<NoteContent>();
		patientHistoryNone.add(new NoteContent(
				"Patient states that they are 'feeling terrific' Weight at or below recommended level"));
		patientHistoryBorderline = new ArrayList<NoteContent>();
		patientHistoryBorderline.add(new NoteContent(
				"Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late"));
		patientHistoryBorderline.add(new NoteContent(
				"Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic"));
		patientHistoryInDanger = new ArrayList<NoteContent>();
		patientHistoryInDanger.add(new NoteContent("Patient states that they are short term Smoker"));
		patientHistoryInDanger.add(new NoteContent(
				"Patient states that they quit within last year Patient also complains that of Abnormal breathing spells Lab reports Cholesterol LDL high"));
		patientHistoryEarlyOnset = new ArrayList<NoteContent>();
		patientHistoryEarlyOnset.add(new NoteContent(
				"Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication"));
		patientHistoryEarlyOnset.add(
				new NoteContent("Patient states that they are experiencing back pain when seated for a long time"));
		patientHistoryEarlyOnset.add(new NoteContent(
				"Patient states that they are a short term Smoker Hemoglobin A1C above recommended level"));
		patientHistoryEarlyOnset.add(
				new NoteContent(" Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction"));
	}

	static void initPatientsInfo() {
		patientInfoNone = new PatientInfo();
		patientInfoNone.setPatientBirthDate(LocalDate.of(1966, 12, 31));
		patientInfoNone.setPatientFirstName("Test");
		patientInfoNone.setPatientLastName("TestNone");
		patientInfoNone.setPatientGender(PatientGender.F);
		patientInfoNone.setPatientId(1L);
		patientInfoBorderline = new PatientInfo();
		patientInfoBorderline.setPatientBirthDate(LocalDate.of(1945, 06, 24));
		patientInfoBorderline.setPatientFirstName("Test");
		patientInfoBorderline.setPatientLastName("TestBorderline");
		patientInfoBorderline.setPatientGender(PatientGender.M);
		patientInfoBorderline.setPatientId(2L);
		patientInfoInDanger = new PatientInfo();
		patientInfoInDanger.setPatientBirthDate(LocalDate.of(2004, 06, 18));
		patientInfoInDanger.setPatientFirstName("Test");
		patientInfoInDanger.setPatientLastName("TestInDanger");
		patientInfoInDanger.setPatientGender(PatientGender.M);
		patientInfoInDanger.setPatientId(3L);
		patientInfoEarlyOnset = new PatientInfo();
		patientInfoEarlyOnset.setPatientBirthDate(LocalDate.of(2002, 06, 28));
		patientInfoEarlyOnset.setPatientFirstName("Test");
		patientInfoEarlyOnset.setPatientLastName("TestEarlyOnset");
		patientInfoEarlyOnset.setPatientGender(PatientGender.F);
		patientInfoEarlyOnset.setPatientId(4L);
	}
}
