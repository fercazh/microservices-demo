package mx.com.fer.todoapi.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "TODO")
public class ToDo {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "ID")
	 private Long id;
	 @Column(name = "USER_ID")
	 @NotNull
	 private Long userId;
	 @Column(name = "TITLE")
	 @NotNull
	 @Size(min = 1, max = 150)
	 private String title;
	 @Column(name = "DESCRIPTION")
	 @Size(min = 1,max = 450)
	 @NotNull
	 private String description;
	 @Column(name = "CREATION", columnDefinition = "TIMESTAMP")
	 @NotNull
	 private LocalDateTime creation;
	 @Column(name = "ESTIMATED_COMPLETION", columnDefinition = "TIMESTAMP")
	 @NotNull
	 private LocalDateTime estimatedCompletion;
	 @Column(name = "COMPLETED", columnDefinition = "TINYINT")
	 @NotNull
	 private Boolean completed;
	 @Column(name = "STATUS")
	 @Enumerated(EnumType.STRING)
	 @NotNull
	 private ToDoStatus status;
	 @Column(name = "DELETED", columnDefinition = "TINYINT")
	 @NotNull
	 private Boolean deleted;
	 @Column(name = "UPDATED_BY")
	 @NotNull
	 private Long updatedBy;
	 @Column(name = "UPDATED_ON", columnDefinition = "TIMESTAMP")
	 @NotNull
	 private LocalDateTime updatedOn;
}
