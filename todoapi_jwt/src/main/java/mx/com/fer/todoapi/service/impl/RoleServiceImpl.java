package mx.com.fer.todoapi.service.impl;


import org.springframework.stereotype.Service;

import mx.com.fer.todoapi.domain.Role;
import mx.com.fer.todoapi.dto.RoleDto;
import mx.com.fer.todoapi.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Override
	public RoleDto buildDTO(Role domain) {
		return RoleDto.builder()
				.id(domain.getId())
				.name(domain.getName())
				.build();
	}

	@Override
	public Role buildDomain(RoleDto dto) {
		return Role.builder()
				.id(dto.getId())
				.name(dto.getName())
				.build();	
		}
}
