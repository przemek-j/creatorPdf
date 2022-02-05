package com.pjada.GeneratorPdf.controllers;

import com.pjada.GeneratorPdf.models.Frame;
import com.pjada.GeneratorPdf.models.Watermark;
import com.pjada.GeneratorPdf.repo.FrameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FrameControler {
    private FrameRepo frameRepo;

    @Autowired
    public FrameControler(FrameRepo frameRepo) {
        this.frameRepo = frameRepo;
    }

    @RequestMapping("/addFrames")
    public String addFrame(@RequestParam("name") String name,
                           @RequestParam("image") String image,
                           Model model)
    throws Exception{
        Frame frame = new Frame(name, image);
        frameRepo.save(frame);
        System.out.println(frame);
        List<Frame> frames = frameRepo.findAll();
        model.addAttribute("frames", frames);
        com.pjada.GeneratorPdf.controllers.pageControler.passUser(model);
        return "frames";
    }


    @RequestMapping("/deleteFrame")
    public String deleteFrame(
            @RequestParam("id")Integer id,
            Model model
    ){
        frameRepo.deleteById(id);
        List<Frame> frames = frameRepo.findAll();
        model.addAttribute("frames", frames);
        com.pjada.GeneratorPdf.controllers.pageControler.passUser(model);
        return "frames";
    }
}
