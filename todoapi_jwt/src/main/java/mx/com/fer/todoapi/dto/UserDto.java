package mx.com.fer.todoapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	private Long id;
	private String role;
	private String username;
	//private String password;
	private Boolean active;
	private LocalDateTime creation;
	private Long updatedBy;
	private LocalDateTime updatedOn;
}
