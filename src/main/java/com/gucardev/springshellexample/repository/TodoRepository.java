package com.gucardev.springshellexample.repository;

import com.gucardev.springshellexample.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
