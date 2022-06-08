package com.example.demo.service;

import com.example.demo.model.Todo;
import com.example.demo.model.TodoValidator;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepository toDoRepository;

    @Autowired
    TodoValidator validator;

    public List<Todo> findAll(Integer limit) {
        return Optional.ofNullable(limit)
                .map(value -> toDoRepository.findAll(PageRequest.of(0, value)).getContent())
                .orElseGet(() -> toDoRepository.findAll());
    }

    public Todo addTodo(Todo todo) {
        if (validator.isValid(todo)) {
            return toDoRepository.save(todo);
        }
        return null;
    }

    public Todo getById(Long id){
        return toDoRepository.getById(id);
    }
    public Todo updateTodo(Todo todo,Long id){
        if (validator.isValid(todo)) {
            todo.setId(id);
            return toDoRepository.save(todo);
        }
        return null;

    }
}
