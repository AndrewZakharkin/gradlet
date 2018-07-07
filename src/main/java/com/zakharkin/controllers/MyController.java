package com.zakharkin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {
    @RequestMapping("cont")
    public @ResponseBody Container cont(@RequestParam(defaultValue = "qwe") String par){
        System.out.println("CONT WAS CALLED");
        return new Container("home ZAK", par);
    }

    class Container{
        private String val1;
        private String val2;

        public Container(){

        }

        public Container(String v1, String v2){
            val1 = v1;
            val2 = v2;
        }

        public String getVal1() {
            return val1;
        }

        public void setVal1(String val1) {
            this.val1 = val1;
        }

        public String getVal2() {
            return val2;
        }

        public void setVal2(String val2) {
            this.val2 = val2;
        }
    }
}
