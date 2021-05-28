package com.diabgnozscreenreportservice.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diabgnozscreenreportservice.domain.PatientInfo;
import com.diabgnozscreenreportservice.domain.PatientRecord;
import com.diabgnozscreenreportservice.proxy.NotesServiceProxy;
import com.diabgnozscreenreportservice.proxy.PatientServiceProxy;

@Service
public class PatientRecordService {

	@Autowired
	private PatientServiceProxy patientServiceProxy;

	@Autowired
	private NotesServiceProxy notesServiceProxy;

	public PatientRecord buildPatientRecord(Long patientId) {

		PatientInfo patientInfo = buildPatientInfo(patientId);
		List<String> patientHistory = buildPatientHistory(patientId);
		int patientAge = calculatePatientAge(patientInfo.getPatientBirthDate());

		return new PatientRecord(patientInfo, patientAge, patientHistory);
	}

	private PatientInfo buildPatientInfo(Long patientId) {
		PatientInfo patientInfo = patientServiceProxy.getPatientInfo(patientId);
		return patientInfo;
	}

	private List<String> buildPatientHistory(Long patientId) {
		return notesServiceProxy.getPatientHistory(patientId).stream().map(nc -> nc.getNoteContent())
				.collect(Collectors.toList());
	}

	private int calculatePatientAge(LocalDate patientBirthDate) {
		return Period.between(patientBirthDate, LocalDate.now()).getYears();
	}

}
