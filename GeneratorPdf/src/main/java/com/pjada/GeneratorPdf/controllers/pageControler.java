package com.pjada.GeneratorPdf.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pageControler {


    @RequestMapping(value = {"/", "index"})
    public String getIndex(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);
        if(currentPrincipalName!="anonymousUser")
            model.addAttribute("userName",currentPrincipalName);
        if(currentPrincipalName.isEmpty() || currentPrincipalName =="anonymousUser")
            model.addAttribute("login","login");
        else
            model.addAttribute("login","logout");
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
