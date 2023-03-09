package mx.com.fer.todoapi.service.impl;

import org.springframework.stereotype.Service;

import mx.com.fer.todoapi.domain.User;
import mx.com.fer.todoapi.dto.UserDto;
import mx.com.fer.todoapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserDto buildDTO(User domain) {
		return UserDto.builder()
		.id(domain.getId())
		.username(domain.getUsername())
		.active(domain.getActive())
		.creation(domain.getCreation())
		.role(domain.getRole().getName())
		.updatedBy(domain.getUpdatedBy())
		.updatedOn(domain.getUpdatedOn())
		.build();
	}

	@Override
	public User buildDomain(UserDto dto) {
		return User.builder()
		.id(dto.getId())
		.username(dto.getUsername())
		.active(dto.getActive())
		.creation(dto.getCreation())
		//.role(dto.getRole())
		.updatedBy(dto.getUpdatedBy())
		.updatedOn(dto.getUpdatedOn())
		.build();
	}
	
}
