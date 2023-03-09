package mx.com.fer.todoapi.dto;

import java.time.LocalDateTime;

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
public class ToDoBasicDto {
	 @NotNull
	 private Long userId;
	 @NotNull
	 @Size(min = 1, max = 150)
	 private String title;
	 @Size(min = 1,max = 450)
	 @NotNull
	 private String description;
	 @NotNull
	 private LocalDateTime estimatedCompletion;
}
