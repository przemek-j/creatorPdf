package com.pjada.GeneratorPdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class GeneratorPdf {
    public GeneratorPdf() {
    }
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
    public void generatePDF(String text, String frame) throws Exception {
        Document doc = new Document();
        File file = new File("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//pdfitext_Test.pdf");
        FileOutputStream pdfFileout = new FileOutputStream(file);
        PdfWriter.getInstance(doc, pdfFileout);
        doc.open();
        if(!frame.equals("empty")) {
            Image background = Image.getInstance("http://localhost:8080/img/" + frame + ".jpg");
            background.scaleAbsolute(doc.getPageSize());
            background.setAlignment(Image.UNDERLYING);
            background.setAbsolutePosition(0, 0);
            doc.add(background);
        }
        if(text.isEmpty())
            text = " ";
        Paragraph para = new Paragraph(text);
        doc.add(para);
        doc.close();
    }
}
