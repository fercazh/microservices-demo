package mx.com.fer.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.fer.todoapi.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
