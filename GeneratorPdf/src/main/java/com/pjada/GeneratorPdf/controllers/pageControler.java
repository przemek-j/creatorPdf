package com.pjada.GeneratorPdf.controllers;

import com.pjada.GeneratorPdf.models.User;
import com.pjada.GeneratorPdf.models.Watermark;
import com.pjada.GeneratorPdf.repo.UserRepo;
import com.pjada.GeneratorPdf.repo.WatermarkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class pageControler {
    @Autowired
    WatermarkRepo watermarkRepo;
    @Autowired
    UserRepo userRepo;

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
    @RequestMapping("getWatermark")
    public String getWatermark(
            @RequestParam("name")String name,
            @RequestParam("image") byte[] img,
            Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepo.findByUserName(userDetails.getUsername());
        List<Watermark> watermarks = watermarkRepo.findAll();
        System.out.println(watermarks);

        return "profile";
    }*/

    @RequestMapping("add")
    public String add(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepo.findByUserName(userDetails.getUsername());
        model = passUser(model);
        if(userDetails.getUsername().equals("admin"))
            return "addFrame";
        else {
            List<Watermark> watermarks = watermarkRepo.findAllByUser_Id(user.get().getId());
            System.out.println(watermarks);
            model.addAttribute("watermarks",watermarks);

            return "profile";
        }

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

    public String encodeImage(byte[] img){
        return Base64.getUrlEncoder().encodeToString(img);
    }




}
