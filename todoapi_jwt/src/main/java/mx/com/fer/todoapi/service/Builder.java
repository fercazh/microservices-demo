package mx.com.fer.todoapi.service;

public interface Builder<DOM,DTO> {
	DTO buildDTO(DOM domain);
	DOM buildDomain(DTO dto);
	
}
