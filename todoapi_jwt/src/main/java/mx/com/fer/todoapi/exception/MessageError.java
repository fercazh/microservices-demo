package mx.com.fer.todoapi.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageError implements Serializable {
	
	private static final long serialVersionUID = -2944813806906749722L;
	private String status;
	private String message;
	private Map<String, String> errors;
	private LocalDateTime date;

}
