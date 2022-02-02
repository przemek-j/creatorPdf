package com.pjada.GeneratorPdf.controllers;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddWatermarkControler {
    WatermarkRepo watermarkRepo;
    UserRepo userRepo;
    @Autowired
    public AddWatermarkControler(WatermarkRepo watermarkRepo, UserRepo userRepo) {
        this.watermarkRepo = watermarkRepo;
        this.userRepo = userRepo;
    }
    @RequestMapping("/addWatermark")
    public String addWatermark(@RequestParam("name") String name,
                           @RequestParam("image") byte[] image,
                           Model model)
            throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepo.findByUserName(userDetails.getUsername());
        Watermark watermark = new Watermark(name, image, user.get());
        watermarkRepo.save(watermark);
        AddUserControler addUserControler = new AddUserControler(userRepo);
        System.out.println(user.get().getId());
        addUserControler.updateUser(user.get().getId(),watermark);
        return "profile";
    }
}
