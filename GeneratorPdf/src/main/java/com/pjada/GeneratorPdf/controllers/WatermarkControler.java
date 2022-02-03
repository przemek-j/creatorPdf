package com.pjada.GeneratorPdf.controllers;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.pjada.GeneratorPdf.FileUploadUtil;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WatermarkControler {
    WatermarkRepo watermarkRepo;
    UserRepo userRepo;
    pageControler pageControler;
    @Autowired
    public WatermarkControler(WatermarkRepo watermarkRepo, UserRepo userRepo) {
        this.watermarkRepo = watermarkRepo;
        this.userRepo = userRepo;
    }
    @RequestMapping("/addWatermark")
    public String addWatermark(@RequestParam("name") String name,
                           @RequestParam("image") MultipartFile image,
                           Model model)
            throws Exception{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepo.findByUserName(userDetails.getUsername());

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uploadDir = "src/main/resources/static/user-watermarks/" + user.get().getId();
        FileUploadUtil.saveFile(uploadDir,fileName,image);

        Watermark watermark = new Watermark(name,"user-watermarks/" + user.get().getId()+ "/" + fileName, user.get());
        watermarkRepo.save(watermark);

        AddUserControler addUserControler = new AddUserControler(userRepo);
        addUserControler.updateUser(user.get().getId(),watermark);

        List<Watermark> watermarks = watermarkRepo.findAllByUser_Id(user.get().getId());
        model.addAttribute("watermarks",watermarks);
        com.pjada.GeneratorPdf.controllers.pageControler.passUser(model);

        return "profile";
    }

    @RequestMapping("/kasuj")
    public String deleteWatermark(
            @RequestParam("id")Integer id,
            Model model
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepo.findByUserName(userDetails.getUsername());

        watermarkRepo.deleteById(id);


        List<Watermark> watermarks = watermarkRepo.findAllByUser_Id(user.get().getId());
        model.addAttribute("watermarks",watermarks);
        com.pjada.GeneratorPdf.controllers.pageControler.passUser(model);
        return "profile";
    }
}

