package com.diabgnozscreenreportservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.diabgnozscreenreportservice.domain.PatientHistoryContent;

@FeignClient(name = "${feignClient.notesService.name}", url = "${feignClient.notesService.url}")
public interface NotesServiceProxy {

	@GetMapping("/diabgnoz/report/patients/{patientId}")
	List<PatientHistoryContent.PatientHistoryNote> getPatientHistory(@PathVariable("patientId") Long patientId);
}
