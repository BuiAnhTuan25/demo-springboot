package com.example.demo.repository;

import com.example.demo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo,Long> {
@Query("select t from Todo t where t.id=:id")
    public Todo getById(@Param("id") Long id);
}
