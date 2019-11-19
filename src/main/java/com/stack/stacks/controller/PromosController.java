package com.stack.stacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PromosController {

    @GetMapping("/promos")
    public String showPromos(){
        return "promos/index";
    }

    @GetMapping("/promos/{id}")
    public String showOnePromo(@PathVariable long id){
        return "promos/show";
    }

}
