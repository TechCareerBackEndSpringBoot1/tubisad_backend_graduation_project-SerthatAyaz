package com.hamitmizrak.ui.mvc.controller;


import com.hamitmizrak.business.dto.TodoRestDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public interface ITodoController {

    //SpeedDataInsert
    public String speedDataInsert();

    //SAVE
    public String todoControllerSaveGetForm(Model model);

    public String todoControllerSavePostForm(TodoRestDto todoRestDto, BindingResult bindingResult);

    //LIST
    public String todoControllerList(Model model);

    //FIND
    public String todoControllerFind(Long id, Model model);

    //DELETE
    public String todoControllerDelete(Long id, Model model);

    //UPDATE
    public String todoControllerUpdateGetForm(Long id, Model model);

    public String todoControllerUpdatePostForm(TodoRestDto todoRestDto, Long id, BindingResult bindingResult);

}
