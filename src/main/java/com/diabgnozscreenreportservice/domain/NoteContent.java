package com.diabgnozscreenreportservice.domain;

import org.springframework.stereotype.Component;

@Component
public class NoteContent {
	
	private String noteContent;

	public NoteContent() {}

	public NoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	@Override
	public String toString() {
		return "NoteContent [noteContent=" + noteContent + "]";
	}
	
	
	
	
}
