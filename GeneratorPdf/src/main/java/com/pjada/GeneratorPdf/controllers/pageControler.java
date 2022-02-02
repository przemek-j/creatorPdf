package com.pjada.GeneratorPdf.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pageControler {


    @RequestMapping(value = {"/", "index"})
    public String getIndex(Model model){
        model = passUser(model);
        return "index";
    }
    @RequestMapping("signUp")
    public String getSignUp(){
        return "signUp";
    }
/*
    @RequestMapping("addFrame")
    public String getaddFrame(){
        return "addFrame";
    }*/

    @RequestMapping("add")
    public String add(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model = passUser(model);
        if(userDetails.getUsername().equals("admin"))
            return "addFrame";
        else
            return "profile";

    }

    public Model passUser(Model m){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if(currentPrincipalName!="anonymousUser")
            m.addAttribute("userName",currentPrincipalName);
        if(currentPrincipalName.isEmpty() || currentPrincipalName =="anonymousUser")
            m.addAttribute("login","login");
        else
            m.addAttribute("login","logout");
        return m;
    }



}
