package com.upendra.microservices.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.microservices.limitservice.bean.LimitConfiguration;
import com.upendra.microservices.limitservice.configuration.Configuration;

@RestController
public class LimitConfigurationController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations()
	{
		return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
		
	}
}
