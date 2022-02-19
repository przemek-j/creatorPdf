package com.pjada.GeneratorPdf.controllers;


import com.pjada.GeneratorPdf.FileUploadUtil;
import com.pjada.GeneratorPdf.GeneratorPdf;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;

@Controller
public class GeneratorPdfControler {
    GeneratorPdf generatorPDF = new GeneratorPdf();

    public GeneratorPdfControler() throws FileNotFoundException {
    }

    @RequestMapping(value = "/generatePdf", method = RequestMethod.POST)
    public void downloadPDF(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("textArea") String text,
                            @RequestParam("frame") String frame,
                            @RequestParam(value = "txt", required = false)MultipartFile txtFile,
                            @RequestParam(value = "img", required = false)MultipartFile imgFile,
                            @RequestParam(value = "background-color", required = false)String color,
                            @RequestParam(value = "marginLeft", required = false)String textMarginLeft,
                            @RequestParam(value = "marginRight", required = false)String textMarginRight,
                            @RequestParam(value = "marginTop", required = false)String textMarginTop,
                            @RequestParam(value = "marginDown", required = false)String textMarginDown,
                            @RequestParam(value = "pictureHorizont")int pictureHorizont,
                            @RequestParam(value = "pictureVertical")int pictureVertical)
            throws Exception {
        Color javaColor = Color.decode(color);
        System.out.println(pictureHorizont);
        System.out.println(pictureVertical);
        generatorPDF.openNewPdf();
        generatorPDF.addBackgroundColor(javaColor);
        generatorPDF.addFrame(frame);

        if(!txtFile.isEmpty() && !imgFile.isEmpty()){
            String fileName = StringUtils.cleanPath(txtFile.getOriginalFilename());
            String uploadDir = "src/main/resources/static/txt/";
            FileUploadUtil.saveFile(uploadDir,fileName,txtFile);
            generatorPDF.addTextFromFile( uploadDir + "/" + fileName,getMargin(textMarginLeft), getMargin(textMarginRight), getMargin(textMarginTop), getMargin(textMarginDown));

            String imgFileName = StringUtils.cleanPath(imgFile.getOriginalFilename());
            String imgUploadDir = "src/main/resources/static/user-img/";
            FileUploadUtil.saveFile(imgUploadDir,imgFileName,imgFile);
            generatorPDF.addImageFromFile("user-img/" + imgFileName, pictureHorizont,pictureVertical);
        }else if(!txtFile.isEmpty()){
            String fileName = StringUtils.cleanPath(txtFile.getOriginalFilename());
            String uploadDir = "src/main/resources/static/txt/";
            FileUploadUtil.saveFile(uploadDir,fileName,txtFile);
            generatorPDF.addTextFromFile( uploadDir + "/" + fileName,getMargin(textMarginLeft), getMargin(textMarginRight), getMargin(textMarginTop), getMargin(textMarginDown));
        }else if(!imgFile.isEmpty()){
            String imgFileName = StringUtils.cleanPath(imgFile.getOriginalFilename());
            String imgUploadDir = "src/main/resources/static/user-img/";
            FileUploadUtil.saveFile(imgUploadDir,imgFileName,imgFile);
            generatorPDF.addImageFromFile("user-img/" + imgFileName,pictureHorizont,pictureVertical);
            generatorPDF.addText(text,getMargin(textMarginLeft), getMargin(textMarginRight), getMargin(textMarginTop), getMargin(textMarginDown));
        }else {
            generatorPDF.addText(text,getMargin(textMarginLeft), getMargin(textMarginRight), getMargin(textMarginTop), getMargin(textMarginDown));
        }
        generatorPDF.closePdf();
        response.setContentType("/pdf");
        response.setHeader("Content-disposition","attachment;filename="+ "testPDF.pdf");
        try {
            File f = new File("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//pdfitext_Test.pdf");
            FileInputStream fis = new FileInputStream(f);
            DataOutputStream os = new DataOutputStream(response.getOutputStream());
            response.setHeader("Content-Length",String.valueOf(f.length()));
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) >= 0) {
                os.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int getMargin(String margin){
        if(margin == null)
            return 0;
        else if(margin.isEmpty())
            return 0;
        else
            return Integer.valueOf(margin);
    }

}
