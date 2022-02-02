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
            model.addAttribute("nam",watermarks.get(0).getName());
            model.addAttribute("imag","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");


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
