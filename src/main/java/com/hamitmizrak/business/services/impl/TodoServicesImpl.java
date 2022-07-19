package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.business.dto.TodoDto;
import com.hamitmizrak.business.services.ITodoServices;
import com.hamitmizrak.data.entity.TodoEntity;
import com.hamitmizrak.data.entity.repository.ITodoRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class TodoServicesImpl implements ITodoServices {

    @Autowired
    ITodoRepository repository;

    @Autowired
    ModelMapper modelMapper;

    // Model Mapper
    // entityToDto
    @Override
    public TodoDto entityToDto(TodoEntity TodoEntity) {
        TodoDto dto=  modelMapper.map(TodoEntity,TodoDto.class);
        return dto;
    }

    // Model Mapper
    // dtoToEntity
    @Override
    public TodoEntity dtoToEntity(TodoDto TodoDto) {
        TodoEntity TodoEntity=    modelMapper.map(TodoDto,TodoEntity.class);
        return TodoEntity;
    }

    // CRUD
    // SAVE
    // http://localhost:8080/save/Todos
    @Override
    @PostMapping("/save/Todos")
    public TodoDto createTodo(TodoDto TodoDto) {
        TodoEntity entity=  dtoToEntity(TodoDto);
        repository.save(entity);
        return TodoDto;
    }


    // LIST
    // http://localhost:8080/list/Todos
    @Override
    @GetMapping("/list/Todos")
    public List<TodoDto> getAllTodos() {
       List<TodoEntity> listem= repository.findAll();
       List<TodoDto> dtoList = new ArrayList<>();
       for(TodoEntity entity   :listem){
           TodoDto dto=  entityToDto(entity);
           dtoList.add(dto);
       }
        return dtoList;
    }

    // FINDBYID
    // http://localhost:8080/find/Todos/1
    @Override
    @GetMapping({"/find/Todos","/find/Todos/{id}"})
    public ResponseEntity<TodoDto> getTodoById(@PathVariable(name="id",required = false) Long id) {
        TodoEntity entity= repository.findById(id).orElseThrow( ()->new ResourceNotFoundException(id+"id yoktur"));
        TodoDto dto=entityToDto(entity);
        return ResponseEntity.ok(dto);
    }

    // DELETE
    // http://localhost:8080/delete/Todos/1
    @Override
    @DeleteMapping({"/delete/Todos","/delete/Todos/{id}"})
    public ResponseEntity<Map<String,Boolean>> deleteTodo(@PathVariable(name="id",required = false) Long id) {
        TodoEntity entity= repository.findById(id).orElseThrow( ()->new ResourceNotFoundException(id+"id yoktur"));
        repository.delete(entity);
        Map<String,Boolean> response=new HashMap<String,Boolean>();
        response.put("Silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // PUT(GÜNCELLEMEK)
    // http://localhost:8080/update/Todos/1
    @Override
    @PostMapping({"/update/Todos","/update/Todos/{id}"})
    public ResponseEntity<TodoDto> updateTodo(@PathVariable(name="id",required = false) Long id, TodoDto TodoDto) {
        TodoEntity entityFind= repository.findById(id).orElseThrow( ()->new ResourceNotFoundException(id+"id yoktur"));
        TodoEntity entity = dtoToEntity(TodoDto);

        entityFind.setTodoName(entity.getTodoName());


        TodoEntity saveEntity=  repository.save(entityFind);

        TodoDto dto= entityToDto(saveEntity);

        return ResponseEntity.ok(dto);
    }
}
