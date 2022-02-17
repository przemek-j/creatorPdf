package com.pjada.GeneratorPdf.controllers;

import com.pjada.GeneratorPdf.models.Frame;
import com.pjada.GeneratorPdf.models.User;
import com.pjada.GeneratorPdf.models.Watermark;
import com.pjada.GeneratorPdf.repo.FrameRepo;
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
    FrameRepo frameRepo;
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
    @RequestMapping("editPdf")
    public String getEditPdf(){
        return "editPdf";
    }


    @RequestMapping("add")
    public String add(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepo.findByUserName(userDetails.getUsername());
        model = passUser(model);
        if(userDetails.getUsername().equals("admin")) {
            List<Frame> frames = frameRepo.findAll();
            System.out.println(frames);
            model.addAttribute("frames", frames);
            return "frames";
        }
        else {
            List<Watermark> watermarks = watermarkRepo.findAllByUser_Id(user.get().getId());
            System.out.println(watermarks);
            model.addAttribute("watermarks",watermarks);

            return "profile";
        }

    }

    public static Model passUser(Model m){
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
