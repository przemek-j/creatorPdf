package com.pjada.GeneratorPdf;

import com.itextpdf.text.Document;
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
}
