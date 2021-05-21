package com.diabgnozscreenreportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.diabgnozscreenreportservice")
public class DiabgnozscreenReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiabgnozscreenReportServiceApplication.class, args);
	}

}
