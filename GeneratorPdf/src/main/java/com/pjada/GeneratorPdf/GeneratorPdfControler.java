package com.pjada.GeneratorPdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class GeneratorPdfControler {


    public void generatePDF(String text) throws Exception {
        Document doc = new Document();
        File file = new File("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//pdfitext_Test.pdf");
        FileOutputStream pdfFileout = new FileOutputStream(file);
        PdfWriter.getInstance(doc, pdfFileout);
        doc.open();
        if(text.isEmpty())
            text = " ";
        Paragraph para = new Paragraph(text);
        doc.add(para);
        doc.close();
    }

    @RequestMapping(value = "/generatePdf", method = RequestMethod.POST)
    public void downloadPDF(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("textArea") String text)
            throws Exception {
        generatePDF(text);
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

}
