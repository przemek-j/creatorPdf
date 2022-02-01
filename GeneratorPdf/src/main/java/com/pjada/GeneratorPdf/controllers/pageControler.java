package com.pjada.GeneratorPdf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pageControler {

    @RequestMapping("index")
    public String getIndex(){

        return "index";
    }
    @RequestMapping("signUp")
    public String getSignUp(){
        return "signUp";
    }

    @RequestMapping("addFrame")
    public String getaddFrame(){
        return "addFrame";
    }
}
