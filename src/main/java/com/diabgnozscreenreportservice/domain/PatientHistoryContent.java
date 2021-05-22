package com.diabgnozscreenreportservice.domain;

import java.util.List;

public class PatientHistoryContent {

	List<PatientHistoryNote> patientHistoryContent;

	public PatientHistoryContent(List<PatientHistoryNote> patientHistoryContent) {
		this.patientHistoryContent = patientHistoryContent;
	}

	public List<PatientHistoryNote> getPatientHistoryContent() {
		return patientHistoryContent;
	}

	public void setPatientHistoryContent(List<PatientHistoryNote> patientHistoryContent) {
		this.patientHistoryContent = patientHistoryContent;
	}

	public static class PatientHistoryNote {

		private String noteContent;

		public PatientHistoryNote() {
		}

		public String getNoteContent() {
			return noteContent;
		}

		public void setNoteContent(String noteContent) {
			this.noteContent = noteContent;
		}

	}
}
