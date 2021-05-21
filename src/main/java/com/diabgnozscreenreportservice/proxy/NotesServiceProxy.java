package com.diabgnozscreenreportservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feignClient.notesService.name}", url = "${feignClient.notesService.url}")
public interface NotesServiceProxy {
	
	@GetMapping("")
	void test();
}
