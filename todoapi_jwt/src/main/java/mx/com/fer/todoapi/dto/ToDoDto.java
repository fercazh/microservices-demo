package mx.com.fer.todoapi.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.fer.todoapi.util.ToDoStatus;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDto {
	 private Long id;
	 @NotNull
	 private Long userId;
	 @NotNull
	 @Size(min = 1, max = 150)
	 private String title;
	 @Size(min = 1,max = 450)
	 @NotNull
	 private String description;
	 @NotNull
	 private LocalDateTime creation;
	 @NotNull
	 private LocalDateTime estimatedCompletion;
	 @NotNull
	 private Boolean completed;
	 @NotNull
	 private ToDoStatus status;
	 @NotNull
	 private Boolean deleted;
	 @NotNull
	 private Long updatedBy;
	 @NotNull
	 private LocalDateTime updatedOn;
}
