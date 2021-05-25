package com.diabgnozscreenreportservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.diabgnozscreenreportservice.domain.PatientInfo;

@FeignClient(name = "${feignClient.patientService.name}", url = "${feignClient.patientService.url}")
public interface PatientServiceProxy {

	@GetMapping("diabgnoz/patients/{patientId}")
	PatientInfo getPatientInfo(@PathVariable("patientId")Long patientId);
	
}
