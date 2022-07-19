package com.hamitmizrak.ui.rest;

import com.hamitmizrak.business.dto.TodoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITodoRest {

    //SAVE
    TodoDto createTodo(TodoDto TodoDto);

    //LIST
    List<TodoDto> getAllTodos();

    //FIND
    ResponseEntity<TodoDto> getTodoById(Long id);

    //DELETE
    ResponseEntity<Map<String,Boolean>> deleteTodo(Long id);

    //UPDATE
    ResponseEntity<TodoDto> updateTodo(Long id, TodoDto TodoDto);
}
