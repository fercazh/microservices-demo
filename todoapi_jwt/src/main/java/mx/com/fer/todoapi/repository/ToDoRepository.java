package mx.com.fer.todoapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.fer.todoapi.domain.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
	@Transactional
	@Modifying
	@Query("update ToDo t set t.deleted = true, t.updatedBy = :userId, t.updatedOn = CURRENT_TIMESTAMP WHERE t.id = :id")
	int deleteToDo(@Param("userId") Long userId, @Param("id") Long id);

	@Transactional
	@Modifying
	@Query("update ToDo t set t.completed = true, t.updatedBy = :userId, t.updatedOn = CURRENT_TIMESTAMP WHERE t.id = :id")
	int completeToDo(@Param("userId") Long userId, @Param("id") Long id);
	
	boolean existsByIdAndUserId(Long id, long userId);
}
