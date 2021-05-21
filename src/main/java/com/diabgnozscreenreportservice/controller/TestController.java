package com.diabgnozscreenreportservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diabgnozscreenreportservice.proxy.PatientServiceProxy;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/diabgnoz-report/patients/")
public class TestController {

	@Autowired
	private PatientServiceProxy proxy;
	
	@GetMapping("")
	public JsonNode test() {
		return proxy.test().get("patientLastName");
	}
	
}
