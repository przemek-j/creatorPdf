package com.pjada.GeneratorPdf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pageControler {

    @RequestMapping("index.html")
    public String getIndex(){
        return "index";
    }
    @RequestMapping("login.html")
    public String getLogin(){
        return "login";
    }
    @RequestMapping("signUp.html")
    public String getSignUp(){
        return "signUp";
    }
}
