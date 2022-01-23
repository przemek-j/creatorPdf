package com.pjada.GeneratorPdf.frame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddFrameControler {
    private FrameRepo frameRepo;

    @Autowired
    public AddFrameControler(FrameRepo frameRepo) {
        this.frameRepo = frameRepo;
    }

    @RequestMapping("/addFrame")
    public String addFrame(@RequestParam("name") String name,
                           @RequestParam("image") String image,
                           Model model)
    throws Exception{
        Frame frame = new Frame(name, image);
        frameRepo.save(frame);
        System.out.println(frame);
        return "index";
    }
}
