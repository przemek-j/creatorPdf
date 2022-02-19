package com.pjada.GeneratorPdf;




import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;


import java.awt.Color;
import java.io.*;
import java.net.MalformedURLException;

public class GeneratorPdf {
    static final String path = "D://uczelnia//s5//programowanie//projekt//GeneratorPdf//pdfitext_Test.pdf";
    PdfDocument pdfDoc;
    Document doc;
    PdfCanvas pdfCanvas;
    public GeneratorPdf() throws FileNotFoundException {
        pdfDoc = new PdfDocument(new PdfWriter(path));
    }
    public void openNewPdf() throws FileNotFoundException {
        pdfDoc = new PdfDocument(new PdfWriter(path));
        doc = new Document(pdfDoc);
        pdfCanvas = new PdfCanvas(pdfDoc.addNewPage());
    }
    public void closePdf(){
        doc.close();
        pdfDoc.close();
    }
    public void addText(String text,int marginL, int marginR, int marginT, int marginD) throws IOException {
        if(text.isEmpty())
            text = " ";
        PdfPage page1 = pdfDoc.getFirstPage();
        PdfCanvas pdfCanvas1 = new PdfCanvas(page1.newContentStreamBefore(), page1.getResources(), pdfDoc);
        System.out.println(PageSize.A4.getWidth());
        System.out.println(PageSize.A4.getHeight());
        if(marginR == 0)
            marginR = (int) PageSize.A4.getWidth();
        if(marginD == 0)
            marginD = (int) PageSize.A4.getHeight();
        Rectangle rectangle = new Rectangle(marginL, (int) PageSize.A4.getHeight() -marginD, marginR-marginL, marginD-marginT);
        pdfCanvas.setFillColor(com.itextpdf.kernel.color.Color.BLACK);
        pdfCanvas1.saveState()
                .rectangle(rectangle)
                .fill()
                .restoreState();
        Canvas canvas = new Canvas(pdfCanvas, pdfDoc, rectangle);
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        Text t = new Text(text).setFont(font).setFontColor(com.itextpdf.kernel.color.Color.BLACK);
        Paragraph para = new Paragraph(text);
        canvas.add(para);
        canvas.close();
    }
    public void addBackgroundColor(Color color){
        System.out.println(color.toString());
        com.itextpdf.kernel.color.Color iTextColor = new DeviceRgb(color.getRed(),color.getGreen(),color.getBlue());
        Rectangle background = new Rectangle(PageSize.A4.getWidth(),PageSize.A4.getHeight());
        pdfCanvas.setFillColor(iTextColor);
        pdfCanvas.rectangle(background);
        pdfCanvas.fill();

    }
    public void addFrame(String frame) throws  IOException {
        if(!frame.equals("empty")) {

            ImageData background = ImageDataFactory.create("http://localhost:8080/img/" + frame + ".jpg");
            Rectangle rectangle = new Rectangle(PageSize.A4.getWidth(),PageSize.A4.getHeight());
            pdfCanvas.addImage(background,rectangle,false);

        }
    }
    public void addTextFromFile(String filePath,int marginL, int marginR, int marginT, int marginD) throws IOException {
        String text = getStringFromFile(filePath);
        addText(text,marginL,marginR,marginT,marginD);
    }
    public void addImageFromFile(String imagePath, int x, int y) throws MalformedURLException {

        ImageData image = ImageDataFactory.create("http://localhost:8080/" + imagePath);
        Rectangle rectangle = new Rectangle(x,y - image.getHeight(),image.getWidth(),image.getHeight());
        pdfCanvas.addImage(image,rectangle,false);
    }

    private String getStringFromFile(String filePath){
        System.out.println(filePath);
        File file = new File("D://uczelnia//s5//programowanie//projekt//GeneratorPdf//" + filePath);
        TxtReader txtReader = new TxtReader(file);
        return txtReader.readFile();
    }

}
