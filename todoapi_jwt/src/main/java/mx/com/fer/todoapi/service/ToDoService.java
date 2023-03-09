package mx.com.fer.todoapi.service;

import java.util.List;

import mx.com.fer.todoapi.domain.ToDo;
import mx.com.fer.todoapi.dto.ToDoBasicDto;
import mx.com.fer.todoapi.dto.ToDoDto;
import mx.com.fer.todoapi.util.ToDoStatus;

public interface ToDoService extends Builder<ToDo, ToDoDto> {
	ToDoDto getById(Long todoId);
	List<ToDoDto> getAll();
	List<ToDoDto> getAll(Long userId);
	List<ToDoDto> getAll(Long userId, ToDoStatus status);
	ToDoDto save(ToDoBasicDto todo);
	ToDoDto save(ToDo todo);
	ToDoDto complete(Long userId, Long todoId);
	ToDoDto delete(Long userId, Long todoId);

}
