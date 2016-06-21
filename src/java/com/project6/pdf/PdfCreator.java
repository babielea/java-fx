package com.project6.pdf;

import com.lowagie.text.*;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.*;
import java.util.*;

/**
 * Created by RottsiK on 20.06.2016.
 */
public class PdfCreator {

    public void Start() throws IOException, DocumentException {

        Document doc = new Document(PageSize.A4);
        OutputStream file = new FileOutputStream("output.pdf");
        PdfWriter writer = PdfWriter.getInstance(doc, file);

        //Header und Footer erstellen
        HeaderFooter event = new HeaderFooter();
        writer.setBoxSize("headFooter", new Rectangle(36, 54, 559, 788));
        writer.setPageEvent(event);

        doc.open();

        //Deckblatt
        GenerateCover(doc);
        doc.newPage();

        //Jahresübersicht
        doc.add(new Phrase("sdaasdsdasd"));
        doc.newPage();
        //Detailansicht

        doc.close();
    }

    public void GenerateCover(Document document) throws IOException {

        HTMLWorker htmlWorker = new HTMLWorker(document);

        FileManager fileManager = new FileManager();
        java.util.List<String> fileContent =  fileManager.readFile("TemplateCover.html");
        StringBuilder stringBuilder = new StringBuilder();

        for (String con : fileContent) {
            stringBuilder.append(con);
        }

        String content = stringBuilder.toString();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = classLoader.getResource("image/logo_kleint.png").getPath();
        content = content.replace("{{Image_Placeholder}}", path);

        htmlWorker.parse(new StringReader(content));
    }

}
