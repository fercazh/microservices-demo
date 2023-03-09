package mx.com.fer.todoapi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
	@NotNull
	@Size(min = 8, max = 20)
	private String username;
	@NotNull
	private String password;
}
