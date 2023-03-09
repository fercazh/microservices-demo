package mx.com.fer.todoapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import mx.com.fer.todoapi.domain.ToDo;
import mx.com.fer.todoapi.dto.ToDoBasicDto;
import mx.com.fer.todoapi.dto.ToDoDto;
import mx.com.fer.todoapi.exception.ToDoException;
import mx.com.fer.todoapi.repository.ToDoRepository;
import mx.com.fer.todoapi.service.ToDoService;
import mx.com.fer.todoapi.util.ToDoStatus;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoRepository toDoRepository;

	@Override
	public ToDoDto getById(Long todoId) {
		return buildDTO(
				toDoRepository.findById(todoId)
				.orElseThrow(()-> new ToDoException(ToDoException.MESSAGE_ID_NOT_FOUND, todoId))
				);
	}
	
	@Override
	public List<ToDoDto> getAll() {
		return toDoRepository.findAll().stream().map(td -> buildDTO(td)).collect(Collectors.toList());
	}

	@Override
	public List<ToDoDto> getAll(Long userId) {
		return getAll(userId, null);
	}

	@Override
	public List<ToDoDto> getAll(Long userId, ToDoStatus status) {
		ToDo todo = new ToDo();
		todo.setUserId(userId);
		todo.setStatus(status);
		todo.setDeleted(false);
		Example<ToDo> e = Example.of(todo, ExampleMatcher.matching().withIgnoreNullValues());
		return toDoRepository.findAll(e).stream().map(td -> buildDTO(td)).collect(Collectors.toList());
	}
	
	@Override
	public ToDoDto save(ToDo todo) {
		return buildDTO(toDoRepository.save(todo));
	}
	
	@Override
	public ToDoDto save(ToDoBasicDto todo) {
		Long updatedBy = 0L;
		ToDo domain = ToDo.builder()
				.completed(false)
				.creation(LocalDateTime.now())
				.deleted(false)
				.description(todo.getDescription())
				.estimatedCompletion(todo.getEstimatedCompletion())
				.status(ToDoStatus.ON_TIME)
				.title(todo.getTitle())
				.updatedBy(updatedBy)
				.updatedOn(LocalDateTime.now())
				.userId(todo.getUserId())
				.build();
		return save(domain);
	}
	
	@Override
	public ToDoDto complete(Long userId, Long todoId) {
		boolean exists = toDoRepository.existsByIdAndUserId(todoId, userId);
		if(exists) {
			int rows = toDoRepository.completeToDo(userId, todoId);
			if (rows >0) {
				return getById(todoId);		
			}else {
				throw new ToDoException(ToDoException.MESSAGE_ID_NOT_FOUND, todoId);
			}
		}else {
			throw new ToDoException(ToDoException.MESSAGE_ID_NOT_FOUND, todoId);
		}
	}

	@Override
	public ToDoDto delete(Long userId, Long todoId) {
		boolean exists = toDoRepository.existsByIdAndUserId(todoId, userId);
		if(exists) {
			int rows = toDoRepository.deleteToDo(userId, todoId);
			if (rows >0) {
				return getById(todoId);		
			}else {
				throw new ToDoException(ToDoException.MESSAGE_ID_NOT_FOUND, todoId);
			}
		}else {
			throw new ToDoException(ToDoException.MESSAGE_ID_NOT_FOUND, todoId);
		}
	}

	@Override
	public ToDo buildDomain(ToDoDto dto) {
		return ToDo.builder()
				.completed(dto.getCompleted())
				.creation(dto.getCreation())
				.deleted(dto.getDeleted())
				.description(dto.getDescription())
				.estimatedCompletion(dto.getEstimatedCompletion())
				.id(dto.getId())
				.status(dto.getStatus())
				.title(dto.getTitle())
				.updatedBy(dto.getUpdatedBy())
				.updatedOn(dto.getUpdatedOn())
				.userId(dto.getUserId())
				.build();
	}

	@Override
	public ToDoDto buildDTO(ToDo domain) {
		return ToDoDto.builder()
				.completed(domain.getCompleted())
				.creation(domain.getCreation())
				.deleted(domain.getDeleted())
				.description(domain.getDescription())
				.estimatedCompletion(domain.getEstimatedCompletion())
				.id(domain.getId())
				.status(domain.getStatus())
				.title(domain.getTitle())
				.updatedBy(domain.getUpdatedBy())
				.updatedOn(domain.getUpdatedOn())
				.userId(domain.getUserId())
				.build();
	}
}
