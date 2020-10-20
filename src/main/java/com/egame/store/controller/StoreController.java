package com.egame.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egame.store.service.StoreService;

@RestController
@RequestMapping("/v1")
public class StoreController {

	private StoreService storeService;

	public StoreController(final StoreService storeService) {
		this.storeService = storeService;
	}
	
	
	

}
