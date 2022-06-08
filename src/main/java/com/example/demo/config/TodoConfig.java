package com.example.demo.config;

import com.example.demo.model.TodoValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoConfig {
    @Bean
    public TodoValidator validator(){
        return new TodoValidator();
    }
}
