package com.example.restwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restwebservices.pojos.HelloWorldBean;

@RestController
public class HelloWorldController {

	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello world";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}
	

	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathvariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world, %s", name));
	}
}
