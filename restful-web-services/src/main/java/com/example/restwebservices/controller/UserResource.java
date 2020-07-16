package com.example.restwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restwebservices.daos.UserDaoService;
import com.example.restwebservices.exceptions.UserNotFoundException;
import com.example.restwebservices.users.User;


@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping(path="/users")
	public List<User> retrieveALlUsers() {
		return userDaoService.retrieveALlUsers();

	}
	@PostMapping(path="/users")
	//public void save(@RequestBody User user)
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser=userDaoService.createUser(user);
		URI location=ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path="/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) throws UserNotFoundException
	{
		User user= userDaoService.findOne(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}
		EntityModel<User> model=new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveALlUsers());
		model.add(linkTo.withRel("all-users"));		
		return model;
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) throws UserNotFoundException
	{
		User user= userDaoService.deleteById(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}
		
	}
}

