package com.hamitmizrak.ui.mvc.controller.impl;

import com.hamitmizrak.business.dto.TodoRestDto;
import com.hamitmizrak.ui.mvc.controller.ITodoController;
import com.sun.xml.bind.v2.TODO;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
public class TodoControllerImp implements ITodoController {

    //speed Insert
    // http://localhost:8080/todo/speedSave
    @Override
    @ResponseBody
    @GetMapping("todo/speedSave")
    public String speedDataInsert() {
        String URL = "http://localhost:8080/api/v1/todo";//dikkat sonuna root yazma
        RestTemplate restTemplate = new RestTemplate();
        int i = 0;
        for (i = 1; i <= 10; i++) {
            TodoRestDto todoRestDto = TodoRestDto.builder().todoId(0L).todoName("New Todo: " + i).build();
            restTemplate.postForObject(URL, todoRestDto, TodoRestDto.class);
        }
        return i + " tane veri eklendi";
    }

    //SAVE GET
    // http://localhost:8080/todo/form
    @GetMapping("todo/form")
    @Override
    public String todoControllerSaveGetForm(Model model) {
        model.addAttribute("todo_save", new TodoRestDto());
        return "todo_save";
    }

    //SAVE GET
    // http://localhost:8080/todo/form
    @Override
    @PostMapping("todo/form")
    public String todoControllerSavePostForm(@Valid @ModelAttribute("todo_save") TodoRestDto todoRestDto, BindingResult bindingResult) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/todo";//dikkat sonuna root yazma
        if(bindingResult.hasErrors()){
            return "todo_save";
        }
        restTemplate.postForObject(URL,todoRestDto,TodoRestDto.class);
        return "redirect:/todo/list";
    }

    //LIST
    // http://localhost:8080/todo/list
    @GetMapping("todo/list")
    @Override
    public String todoControllerList(Model model) {
         RestTemplate restTemplate = new RestTemplate();
         String URL = "http://localhost:8080/api/v1/todo";//dikkat sonuna root yazma
        ResponseEntity<List<TodoRestDto>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<TodoRestDto>>() {
        });
        List<TodoRestDto> dtoList = responseEntity.getBody();
        model.addAttribute("todo_list", dtoList);
        return "todo_list";
    }

    //FIND
    // http://localhost:8080/find/todo/4
    @GetMapping("find/todo/{id}")
    @Override
    public String todoControllerFind(@PathVariable(name="id") Long id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/todo/"+id;//dikkat sonuna root yazma
        ResponseEntity<TodoRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY,TodoRestDto.class);
        model.addAttribute("todo_find",responseEntity.getBody());
        return "todo_detail_pages";
    }

    //DELETE
    // http://localhost:8080/delete/todo/4
    @GetMapping("delete/todo/{id}")
    @Override
    public String todoControllerDelete(@PathVariable(name="id") Long id, Model model) {
        //"http://localhost:8080/api/v1/todo"  /1
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/todo/"+id;//dikkat sonuna root yazma
        ResponseEntity<TodoRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.DELETE, HttpEntity.EMPTY,TodoRestDto.class);
        model.addAttribute("todo_delete",responseEntity.getBody());
        return "redirect:/todo/list";
    }


    //UPDATE GET
    @Override
    @GetMapping("update/todo/{id}")
    public String todoControllerUpdateGetForm(@PathVariable(name="id") Long id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/todo/"+id;//dikkat sonuna root yazma
        ResponseEntity<TodoRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY,TodoRestDto.class);
        model.addAttribute("todo_update",responseEntity.getBody());
        return "todo_update";
    }


    //UPDATE POST
    @Override
    @PostMapping("update/todo/{id}")
    public String todoControllerUpdatePostForm(@Valid @ModelAttribute("todo_update")  TodoRestDto todoRestDto, @PathVariable(name="id") Long id, BindingResult bindingResult) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/todo";//dikkat sonuna root yazma
        if(bindingResult.hasErrors()){
            return "todo_update";
        }
        restTemplate.postForObject(URL,todoRestDto, TodoRestDto.class);
        return "redirect:/todo/list";
    }
}
