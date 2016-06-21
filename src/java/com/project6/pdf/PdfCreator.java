package com.project6.pdf;

import com.lowagie.text.*;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;

import javax.print.attribute.standard.PagesPerMinute;
import java.io.*;
import java.util.Date;

/**
 * Hilfsklasse zur Erstellung der PDF.
 * Die PDF's werden mit Hilfe der HTML Templates dynamisch generiert.
 * Hierzu sind bestimmte Platzhalter definiert wurden, diese sind an den geschweiften Klammer zu erkennen.
 * z.B. {{Ausbildungsjahr}}
 * Created by RottsiK on 20.06.2016.
 */
public class PdfCreator {
    private final String TEMPLATE_COVER = "TemplateCover.html";
    private final String TEMPLATE_JAHRESPLAN = "TemplateJahresplan.html";
    private final String TEMPLATE_DETAILS = "TemplateDetail.html";

    private FileManager fileManager = new FileManager();

    public void Start() throws IOException, DocumentException {

        Document doc = new Document(PageSize.A4);
        Date today = new Date();
        //Dateiname z.B. 20160621_Output.pdf
        OutputStream file = new FileOutputStream(String.format("%tY%tm%td_Output.pdf", today, today, today));
        PdfWriter writer = PdfWriter.getInstance(doc, file);

        //Header und Footer erstellen
        HeaderFooter event = new HeaderFooter();
        writer.setBoxSize("headFooter", new Rectangle(36, 54, 559, 788));
        writer.setPageEvent(event);

        doc.open();

        //Deckblatt erstellen
        //TODO: MUSS GEÄNDERT WERDEN
        GenerateCover(doc, "2", "Fachinformatiker AE", "Herr Gottwald", "Frau Dresen");

        //Jahresübersicht
        doc.setPageSize(PageSize.A4.rotate());
        doc.newPage();
        GenerateJahresplan(doc);

        //Detailansicht
        doc.setPageSize(PageSize.A4);
        doc.newPage();
        GenerateDetail(doc);

        doc.close();
    }

    /**
     * Generiert aus der TemplateCover.html Datei das Deckblatt
     *
     * @param document             Document
     * @param yearOfApprenticeship Ausbildungsjahr
     * @param qualifiedJob         Ausbildungsberuf
     * @param bildungsgangsleitung Bildungsgangsleitung
     * @param bereichsleitung      Bereichsleitung
     * @throws IOException Datei nicht gefunden
     */
    private void GenerateCover(Document document,
                               String yearOfApprenticeship,
                               String qualifiedJob,
                               String bildungsgangsleitung,
                               String bereichsleitung) throws IOException {
        HTMLWorker htmlWorker = new HTMLWorker(document);
        String content = fileManager.getFullFileContent(TEMPLATE_COVER);

        //Resourcen Pfad auslesen
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String logoPath = classLoader.getResource("image/logo_kleint.png").getPath();

        //Die Platzhalter ersetzen
        content = content.replace(Placeholder.IMAGEPATH, logoPath);
        content = content.replace(Placeholder.YEAROFAPPRENTICESHIP, yearOfApprenticeship);
        content = content.replace(Placeholder.QUALIFIEDJOB, qualifiedJob);
        content = content.replace(Placeholder.BILDUNGSGANGSLEITUNG, bildungsgangsleitung);
        content = content.replace(Placeholder.BEREICHSLEITUNG, bereichsleitung);

        htmlWorker.parse(new StringReader(content));
    }

    private void GenerateJahresplan(Document document) throws IOException {

        HTMLWorker htmlWorker = new HTMLWorker(document);
        String content = fileManager.getFullFileContent(TEMPLATE_JAHRESPLAN);

        //Placeholder ersetzen

        htmlWorker.parse(new StringReader(content));
    }

    public void GenerateDetail(Document document) throws IOException {
        HTMLWorker htmlWorker = new HTMLWorker(document);
        String content = fileManager.getFullFileContent(TEMPLATE_DETAILS);

        //Placeholder ersetzen

        htmlWorker.parse(new StringReader(content));
    }
}
