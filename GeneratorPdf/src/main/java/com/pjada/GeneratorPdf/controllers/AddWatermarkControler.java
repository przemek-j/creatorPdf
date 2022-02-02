package com.pjada.GeneratorPdf.controllers;

import java.awt.*;
import com.pjada.GeneratorPdf.models.Watermark;
import com.pjada.GeneratorPdf.repo.WatermarkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddWatermarkControler {
    WatermarkRepo watermarkRepo;
    @Autowired
    public AddWatermarkControler(WatermarkRepo watermarkRepo) {
        this.watermarkRepo = watermarkRepo;
    }
    @RequestMapping("/addWatermark")
    public String addFrame(@RequestParam("name") String name,
                           @RequestParam("image") byte[] image,
                           Model model)
            throws Exception{
        Watermark watermark = new Watermark(name, image);
        watermarkRepo.save(watermark);
        return "profile";
    }
}
