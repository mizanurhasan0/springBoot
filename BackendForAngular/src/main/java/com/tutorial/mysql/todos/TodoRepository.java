package com.tutorial.mysql.todos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todos, Long>{
	// List<Todos> showByUsername(String username);
}
