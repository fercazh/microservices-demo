package mx.com.fer.todoapi.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
//	@Column(name = "ROLE_ID")
//	@NotNull
//	private Integer roleId;
	

	@NotNull
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "ROLE_ID", updatable = true, insertable = true)
	@OneToOne(fetch = FetchType.EAGER)
	private Role role;
	
	@Column(name = "USERNAME")
	@NotNull
	@Size(min = 8, max = 25)
	private String username;
	@Column(name = "PASSWORD")
	@NotNull
	@Size(max = 250)
	private String password;
	@Column(name = "ACTIVE", columnDefinition = "TINYINT")
	@NotNull
	private Boolean active;
	@Column(name = "CREATION", columnDefinition = "TIMESTAMP")
	@NotNull
	private LocalDateTime creation;
	@Column(name = "UPDATED_BY")
	@NotNull
	private Long updatedBy;
	@Column(name = "UPDATED_ON", columnDefinition = "TIMESTAMP")
	@NotNull
	private LocalDateTime updatedOn;
	
}
