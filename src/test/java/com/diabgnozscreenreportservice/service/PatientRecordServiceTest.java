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

import com.diabgnozscreenreportservice.domain.NoteContent;
import com.diabgnozscreenreportservice.domain.PatientInfo;
import com.diabgnozscreenreportservice.domain.PatientRecord;
import com.diabgnozscreenreportservice.proxy.NotesServiceProxy;
import com.diabgnozscreenreportservice.proxy.PatientServiceProxy;
import com.diabgnozscreenreportservice.utility.PatientGender;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PatientRecordServiceTest {

	@InjectMocks
	private PatientRecordService patientRecordService;

	@Mock
	private PatientServiceProxy patientServiceProxy;

	@Mock
	private NotesServiceProxy notesServiceProxy;

	private static PatientInfo patientInfo;
	private static List<NoteContent> patientHistory;
	
	@BeforeAll
	static void setUp() {
		initPatientHistory();
		initPatientInfo();
	}
	
	@Test
	void buildPatientRecordTest() {
		when(patientServiceProxy.getPatientInfo(1L)).thenReturn(patientInfo);
		when(notesServiceProxy.getPatientHistory(1L)).thenReturn(patientHistory);
		PatientRecord patientRecord = patientRecordService.buildPatientRecord(1L);
		assertEquals(54,patientRecord.getPatientAge());
	}

	static void initPatientHistory() {
		patientHistory = new ArrayList<NoteContent>();
		patientHistory.add(new NoteContent("Patient states that they are 'feeling terrific' Weight at or below recommended level"));
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
