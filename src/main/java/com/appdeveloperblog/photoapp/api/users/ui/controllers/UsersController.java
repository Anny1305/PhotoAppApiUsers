package com.appdeveloperblog.photoapp.api.users.ui.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdeveloperblog.photoapp.api.users.service.UsersService;
import com.appdeveloperblog.photoapp.api.users.shared.UserDto;
import com.appdeveloperblog.photoapp.api.users.ui.model.CreateUserRequestModel;
import com.appdeveloperblog.photoapp.api.users.ui.model.CreateUserResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	Environment env;

	@GetMapping("/status/check")
	public String status() {
		return "working!! "+env.getProperty("local.server.port");
	}
	
	@Autowired
	UsersService userService;
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
	             produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
                )
	public ResponseEntity createUser(@Valid @RequestBody CreateUserRequestModel userModel){
		
		
		ModelMapper mp = new ModelMapper();
		mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto ud  = mp.map(userModel, UserDto.class);
		
		UserDto createdUser = userService.createUsers(ud);
		
		CreateUserResponseModel createUserResponseModel  = mp.map(createdUser, CreateUserResponseModel.class);

		return  ResponseEntity.status(HttpStatus.CREATED).body(createUserResponseModel);

	}	
}
