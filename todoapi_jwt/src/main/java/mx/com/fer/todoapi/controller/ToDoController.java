package mx.com.fer.todoapi.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mx.com.fer.todoapi.dto.ToDoBasicDto;
import mx.com.fer.todoapi.dto.ToDoDto;
import mx.com.fer.todoapi.service.ToDoService;
import mx.com.fer.todoapi.util.ToDoStatus;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/todo")
@RestController
public class ToDoController {
	@Autowired
	ToDoService toDoService;

	@GetMapping(  path = "/"
			, produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> getAll() {
		Map<String, Object> result = new HashMap<>();
		result.put("date", LocalDateTime.now());
		try {
			List<ToDoDto> list = toDoService.getAll();
			result.put("status", "OK");
			result.put("result", list);
			return ResponseEntity.ok(result);
		}catch (Exception e){
			result.put("status", "ERROR");
			result.put("error", "something was wrong");
			return ResponseEntity.badRequest().body(result);
		}
	}

	@GetMapping(  path = "/{userId}"
			, produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> getAllByStatus(
			@PathVariable Long userId, 
			@RequestParam(name = "status", required = false) ToDoStatus status) {
		Map<String, Object> result = new HashMap<>();
		result.put("date", LocalDateTime.now());
		try {
			List<ToDoDto> list = toDoService.getAll(userId, status);
			result.put("status", "OK");
			result.put("result", list);
			return ResponseEntity.ok(result);
		}catch (Exception e){
			result.put("status", "ERROR");
			result.put("message", "something was wrong");
			return ResponseEntity.badRequest().body(result);
		}
	}

	@PostMapping( path = "/"
			, consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(@Validated @RequestBody ToDoBasicDto todo) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("date", LocalDateTime.now());
		try {
			ToDoDto td = toDoService.save(todo);
			result.put("status", "OK");
			result.put("message", String.format("ToDo '%s' was saved with the number '%s' at '%s'.",td.getTitle().toUpperCase(), td.getId(), td.getCreation()));
			result.put("result", td);
			return ResponseEntity.ok(result);
		}catch (Exception e){
			result.put("status", "ERROR");
			result.put("message", "something was wrong");
			return ResponseEntity.badRequest().body(result);
		}
	}
	
	@PatchMapping(path = "/{userId}/{todoId}"
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> complete(@PathVariable Long userId, @PathVariable Long todoId) {
		Map<String, Object> result = new HashMap<>();
		result.put("date", LocalDateTime.now());
		ToDoDto dto = toDoService.complete(userId, todoId);
		result.put("status", "OK");
		result.put("message", String.format("Congratulations!!, ToDo '%s', with id: '%s' was Completed.",dto.getTitle().toUpperCase(), todoId));
		result.put("result", dto);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/{userId}/{todoId}"
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Long userId, @PathVariable Long todoId) {
		Map<String, Object> result = new HashMap<>();
		result.put("date", LocalDateTime.now());
		ToDoDto dto = toDoService.delete(userId, todoId);
		result.put("status", "OK");
		result.put("message", String.format("ToDo '%s', with id: '%s' was Deleted.",dto.getTitle().toUpperCase(), todoId));
		result.put("result", dto);
		return ResponseEntity.ok(result);
	}
}
