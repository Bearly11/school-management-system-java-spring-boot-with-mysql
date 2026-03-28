package com.setec.school_management.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class HomeController {

    @GetMapping
    public String home(){
        return"home page";
    }
    
    @GetMapping("/{id}")
    public int findById(@PathVariable int id){
        return id;
    }


    @PostMapping("/create")
    public String create(@RequestBody String name){
        return ""+name;
    }

    @PutMapping("/update/{id}")
    public String updateById(@PathVariable int id, @RequestBody String name){
        return ""+ name;
    }

    @DeleteMapping("/delete/{id}")
    public int deleteById(@PathVariable int id){
        return id;
    }

}
