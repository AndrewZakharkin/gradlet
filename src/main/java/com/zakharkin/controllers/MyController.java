package com.zakharkin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping("qqq")
    public @ResponseBody String cont(){
        System.out.println("CONT WAS CALLED");
        return "home ZAK";
    }
}
