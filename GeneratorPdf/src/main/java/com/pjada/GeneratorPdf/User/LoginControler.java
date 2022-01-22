package com.pjada.GeneratorPdf.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControler {
    private UserRepo userRepo;
    @Autowired
    public LoginControler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping(value = "/log-in",method = RequestMethod.POST)
    public String login(
            @RequestParam("name") String name,
            @RequestParam("password") String pass,
            Model model)
            throws Exception{
        User user = new User(name,pass);
        if(userRepo.exists(Example.of(user))) {
            model.addAttribute("login","success");
            return "index";
        }
        else {
            model.addAttribute("login","Nie poprawne dane logowania");
            return "login";
        }

    }


}
