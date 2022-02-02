package com.pjada.GeneratorPdf.controllers;

import com.pjada.GeneratorPdf.models.User;
import com.pjada.GeneratorPdf.models.Watermark;
import com.pjada.GeneratorPdf.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class AddUserControler {
    private UserRepo userRepo;
    @Autowired
    public AddUserControler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(
            @RequestParam("name") String name,
            @RequestParam("password") String pass,
                Model model)
        throws Exception{
        User user = new User(name,pass,"ROLE_USER");
        userRepo.save(user);

        System.out.println(user);
        model.addAttribute("login","login");
        return "index";
    }

    public void updateUser(Integer id, Watermark watermark){
        Optional<User> user = userRepo.findById(id);
        System.out.println(user);
        List<Watermark> userWatermarks = user.get().getWatermarks();
        userWatermarks.add(watermark);
        user.get().setWatermarks(userWatermarks);
        userRepo.save(user.get());
    }

}
