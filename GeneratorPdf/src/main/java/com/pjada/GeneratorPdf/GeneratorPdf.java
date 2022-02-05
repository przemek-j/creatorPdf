package com.pjada.GeneratorPdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.io.IOUtils;


import java.awt.*;
import java.io.*;

public class GeneratorPdf {
    public GeneratorPdf() {
    }
    public void generatePDF(String text, Color color) throws Exception {
        Document doc = new Document();
        File file = new File("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//pdfitext_Test.pdf");
        FileOutputStream pdfFileout = new FileOutputStream(file);
        PdfWriter.getInstance(doc, pdfFileout);
        doc.open();
        Rectangle background = new Rectangle(doc.getPageSize());
        background.setBackgroundColor(new BaseColor(color.getRed(),color.getGreen(),color.getBlue()));
        doc.add(background);
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

    public void generatePDF(String text, String frame, String filePath) throws Exception {
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
            text = getStringFromFile(filePath);
        Paragraph para = new Paragraph(text);
        doc.add(para);
        doc.close();
    }
    public void generatePDF(String text, String frame, String filePath, String imagePath) throws Exception {
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
            text = getStringFromFile(filePath);
        Paragraph para = new Paragraph(text);
        doc.add(para);

        Image image = Image.getInstance("http://localhost:8080/" + imagePath);
        doc.add(image);
        doc.close();
    }



    private String getStringFromFile(String filePath){
        System.out.println(filePath);
        File file = new File("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//" + filePath);
        TxtReader txtReader = new TxtReader(file);
        return txtReader.readFile();
    }

}
