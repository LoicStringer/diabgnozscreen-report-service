package com.diabgnozscreenreportservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.JsonNode;

@FeignClient(name = "${feignClient.patientService.name}", url = "${feignClient.patientService.url}")
public interface PatientServiceProxy {

	@GetMapping("diabgnoz/patients/1")
	JsonNode test();
	
}
