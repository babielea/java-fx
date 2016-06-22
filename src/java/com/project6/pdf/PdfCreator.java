package com.project6.pdf;

import com.lowagie.text.*;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfWriter;
import com.project6.model.domain.DataForCover;
import com.project6.model.domain.Lernfeld;
import com.project6.model.domain.Lernsituation;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hilfsklasse zur Erstellung der PDF.
 * Die PDF's werden mit Hilfe der HTML Templates dynamisch generiert.
 * Hierzu sind bestimmte Platzhalter definiert wurden, diese sind an den geschweiften Klammer zu erkennen.
 * z.B. {{Ausbildungsjahr}}
 * Created by RottsiK on 20.06.2016.
 */
public class PdfCreator {

    /**
     * Template Datei fuer das Deckblatt
     */
    private final String TEMPLATE_COVER = "TemplateCover.html";

    /**
     * Template Datei fuer den Jahresplaner
     */
    private final String TEMPLATE_JAHRESPLAN = "TemplateJahresplan.html";

    /**
     * Template Datei fuer die Detailansicht
     */
    private final String TEMPLATE_DETAILS = "TemplateDetail.html";

    /**
     * Hilfsklasse fuer die Dateiverwaltung
     */
    private FileManager fileManager = new FileManager();

    public void CreatePDF(List<Lernfeld> lernfelder, DataForCover dataForCover) throws IOException, DocumentException {

        Document doc = new Document(PageSize.A4);
        Date today = new Date();
        //Dateiname z.B. 20160621_output.pdf
        OutputStream file = new FileOutputStream(String.format("%tY%tm%td_output.pdf", today, today, today));
        PdfWriter writer = PdfWriter.getInstance(doc, file);

        //Header und Footer erstellen
        HeaderFooter event = new HeaderFooter();
        writer.setBoxSize("headFooter", new Rectangle(36, 54, 559, 788));
        writer.setPageEvent(event);

        doc.open();
        doc.addCreator("PDF-Creator © HHBK 2016 (Rottsieper, Aslan, Sulik, Klemm)");
        doc.addTitle("Didaktischer Wizard - Übersicht");

        //Deckblatt erstellen
        GenerateCover(doc,
                dataForCover.getYearOfAppentice(),
                dataForCover.getApprenticeJob(),
                dataForCover.getBildungsgangsleitung(),
                dataForCover.getBereichsleitung());

        //Jahresübersicht
        doc.setPageSize(PageSize.A4.rotate());
        doc.newPage();
        GenerateJahresplan(doc, lernfelder);

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
                               int yearOfApprenticeship,
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
        content = content.replace(Placeholder.YEAROFAPPRENTICESHIP, Integer.toString(yearOfApprenticeship));
        content = content.replace(Placeholder.QUALIFIEDJOB, qualifiedJob);
        content = content.replace(Placeholder.BILDUNGSGANGSLEITUNG, bildungsgangsleitung);
        content = content.replace(Placeholder.BEREICHSLEITUNG, bereichsleitung);

        htmlWorker.parse(new StringReader(content));
    }

    private void GenerateJahresplan(Document document, List<Lernfeld> lernfelder) throws IOException {

        HTMLWorker htmlWorker = new HTMLWorker(document);
        String content = fileManager.getFullFileContent(TEMPLATE_JAHRESPLAN);
        StringBuilder stringBuilder = new StringBuilder();

        String previousNameDefault = "";
        String previousFach = "";
        Boolean initializeWrite = false;

        //Placeholder ersetzen
        for (Lernfeld lernfeld : lernfelder) {

            //Überprüfen, ob schonmal ein NameDefault gesetzt wurde, wenn nicht dann generiere den NameDefault.
            if (!initializeWrite) {
                stringBuilder.append(GetHTMLCode(lernfeld.getNameDefault(), 12, ""));
                previousNameDefault = lernfeld.getNameDefault();

                stringBuilder.append(GetHTMLCode(lernfeld.getFach(), 12, ""));
                previousFach = lernfeld.getFach();
                initializeWrite = true;
            } else {
                //Sind die NameDefault's unterschiedlich, dann generiere den neuen NameDefault
                if (!previousNameDefault.equalsIgnoreCase(lernfeld.getNameDefault())) {
                    stringBuilder.append(GetHTMLCode(lernfeld.getNameDefault(), 12, ""));
                    previousNameDefault = lernfeld.getNameDefault();
                }

                //Sind die Fächer unterschiedlich, dann generiere das neue Fach
                if(!previousFach.equalsIgnoreCase(lernfeld.getFach())) {
                    stringBuilder.append(GetHTMLCode(lernfeld.getFach(), 12, "background-color:lightgreen"));
                    previousFach = lernfeld.getFach();
                }
            }

            for (Lernsituation lernsituation : lernfeld.getLernsituationen()) {

            }
        }

        content = content.replace(Placeholder.FOREACH_LERNSTATIONEN, stringBuilder.toString());

        htmlWorker.parse(new StringReader(content));
    }

    public void GenerateDetail(Document document) throws IOException {
        HTMLWorker htmlWorker = new HTMLWorker(document);
        String content = fileManager.getFullFileContent(TEMPLATE_DETAILS);

        //Placeholder ersetzen

        htmlWorker.parse(new StringReader(content));
    }

    private String GetHTMLCode(String value, int colspan, String style) {
        String ret = String.format("<tr><td colspan=\"%d\" style=\"%s\">%s</td></tr>", colspan, style, value);
        return ret;
    }
}
