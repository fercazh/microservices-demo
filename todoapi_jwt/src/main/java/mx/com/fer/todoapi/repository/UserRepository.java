package mx.com.fer.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.fer.todoapi.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
