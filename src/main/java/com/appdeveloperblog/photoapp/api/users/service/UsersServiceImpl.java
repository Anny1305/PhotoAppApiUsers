package com.appdeveloperblog.photoapp.api.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appdeveloperblog.photoapp.api.users.data.UserEntity;
import com.appdeveloperblog.photoapp.api.users.data.UsersRepository;
import com.appdeveloperblog.photoapp.api.users.shared.UserDto;

@Service
public class UsersServiceImpl  implements UsersService
{
	UsersRepository usersRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
	public UsersServiceImpl(UsersRepository usersRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto createUsers(UserDto userDetails) {
		
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity =  mm.map(userDetails, UserEntity.class);
        
        usersRepository.save(userEntity);
        
        UserDto returnValue =  mm.map(userEntity,UserDto.class);
        
		
		return returnValue;
		
		
	}

}
