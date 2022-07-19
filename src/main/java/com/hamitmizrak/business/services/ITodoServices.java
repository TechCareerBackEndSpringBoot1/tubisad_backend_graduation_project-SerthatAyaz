package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.TodoDto;
import com.hamitmizrak.data.entity.TodoEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITodoServices {

    //Model Mapper
    public TodoDto  entityToDto(TodoEntity TodoEntity);
    public TodoEntity  dtoToEntity(TodoDto TodoDto);

    //save
    public TodoDto createTodo(TodoDto TodoDto);

    //list
    public List<TodoDto> getAllTodos();

    //find
    public ResponseEntity<TodoDto> getTodoById(Long id);

    //delete
    public ResponseEntity<Map<String,Boolean>> deleteTodo(Long id);

    //update
    public ResponseEntity<TodoDto> updateTodo(Long id,TodoDto TodoDto);
}
