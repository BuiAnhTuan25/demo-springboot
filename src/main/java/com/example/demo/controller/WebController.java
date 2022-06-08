package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    TodoService todoService;

    @GetMapping("/listTodo")
    public String index(Model model, @RequestParam(value = "limit", required = false) Integer limit) {
        // Trả về đối tượng todoList.
        // Nếu người dùng gửi lên param limit thì trả về sublist của todoList
        model.addAttribute("todoList", todoService.findAll(limit));

        // Trả về template "listTodo.html"
        return "listTodo";
    }
    @GetMapping("/addTodo")
    public String addTodo(Model model) {
        // Thêm mới một đối tượng Todo vào model
        model.addAttribute("todo", new Todo());
        // Trả về template addTodo.html
        return "addTodo";
    }
    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute Todo todo) {
        return Optional.ofNullable(todoService.addTodo(todo))
                .map(t->"success")
                .orElse("false");
    }
    @GetMapping("/todo/{id}")
    public String getById(@PathVariable Long id,Model model){
        model.addAttribute("todo",todoService.getById(id));
        return "todo";
    }
    @PostMapping("/todo/{id}")
    public String updateTodo(@ModelAttribute Todo todo,@PathVariable Long id){
        return Optional.ofNullable(todoService.updateTodo(todo,id))
                .map(t->"update-success")
                .orElse("false");
    }
}
