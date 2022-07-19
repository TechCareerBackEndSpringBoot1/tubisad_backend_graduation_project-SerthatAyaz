package com.hamitmizrak.ui.rest.impl;

import com.hamitmizrak.business.dto.TodoDto;
import com.hamitmizrak.business.services.ITodoServices;
import com.hamitmizrak.ui.rest.ITodoRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
//Dış dünyaya açılacak kapı ...
public class TodoImpl implements ITodoRest {

    @Autowired
    ITodoServices services;

    //http://localhost:8080/api/v1
    //http://localhost:8080/api/v1/index
    @GetMapping({"/","/index"})
    public String getRoot(){
        return "index";
    }


    //SAVE
    //http://localhost:8080/api/v1/Todos
    @Override
    @PostMapping("/Todos")
    public TodoDto createTodo( @RequestBody TodoDto TodoDto) {
        services.createTodo(TodoDto);
        return TodoDto;
    }

    //LIST
    //http://localhost:8080/api/v1/Todos
    @Override
    @GetMapping("/Todos")
    public List<TodoDto> getAllTodos() {
        List<TodoDto> list=services.getAllTodos();
        return list;
    }

    //FIND
    //http://localhost:8080/api/v1/Todos/1
    @Override
    @GetMapping ({"/Todos/{id}","/Todos/"})
    public ResponseEntity<TodoDto> getTodoById(@PathVariable(name="id",required = false) Long id) {
        ResponseEntity<TodoDto> dto= services.getTodoById(id);
        return dto;
    }

    //DELETE
    //http://localhost:8080/api/v1/Todos/1
    @Override
    @DeleteMapping({"/Todos/{id}","/Todos/"})
    public ResponseEntity<Map<String, Boolean>> deleteTodo(@PathVariable(name="id",required = false) Long id) {
        services.deleteTodo(id);
        Map<String,Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //UPDATE
    //http://localhost:8080/api/v1/Todos/1
    @Override
    @PostMapping("/Todos/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable(name="id") Long id, @RequestBody TodoDto TodoDto) {
       services.updateTodo(id,TodoDto);
        return ResponseEntity.ok(TodoDto);
    }
}
