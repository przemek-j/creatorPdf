package com.pjada.GeneratorPdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.io.IOUtils;


import java.awt.*;
import java.io.*;

public class GeneratorPdf {
    Document doc;
    public GeneratorPdf() {
        doc = new Document();
    }
    public void openNewPdf() throws FileNotFoundException, DocumentException {
        doc = new Document();
        File file = new File("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//pdfitext_Test.pdf");
        FileOutputStream pdfFileout = new FileOutputStream(file);
        PdfWriter.getInstance(doc, pdfFileout);
        doc.open();
    }
    public void openNewPdf(String path) throws FileNotFoundException, DocumentException {
        doc = new Document();

        System.out.println("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//" + path);
        File file = new File("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//" + path);
        FileOutputStream pdfFileout = new FileOutputStream(file);
        PdfWriter.getInstance(doc, pdfFileout);
        doc.open();
    }
    public void closePdf(){
        doc.close();
    }
    public void addText(String text) throws DocumentException {
        if(text.isEmpty())
            text = " ";
        Paragraph para = new Paragraph(text);
        para.setIndentationLeft(30);
        doc.add(para);
    }
    public void addBackgroundColor(Color color) throws DocumentException {
        Rectangle background = new Rectangle(doc.getPageSize());
        background.setBackgroundColor(new BaseColor(color.getRed(),color.getGreen(),color.getBlue()));
        doc.add(background);
    }
    public void addFrame(String frame) throws DocumentException, IOException {
        if(!frame.equals("empty")) {
            Image background = Image.getInstance("http://localhost:8080/img/" + frame + ".jpg");
            background.scaleAbsolute(doc.getPageSize());
            background.setAlignment(Image.UNDERLYING);
            background.setAbsolutePosition(0, 0);
            doc.add(background);
        }
    }
    public void addTextFromFile(String filePath) throws DocumentException {
        String text = getStringFromFile(filePath);
        Paragraph para = new Paragraph(text);
        para.setIndentationLeft(30);
        doc.add(para);
    }
    public void addImageFromFile(String imagePath) throws DocumentException, IOException {
        Image image = Image.getInstance("http://localhost:8080/" + imagePath);
        doc.add(image);
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

    private String getStringFromFile(String filePath){
        System.out.println(filePath);
        File file = new File("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//" + filePath);
        TxtReader txtReader = new TxtReader(file);
        return txtReader.readFile();
    }

}
