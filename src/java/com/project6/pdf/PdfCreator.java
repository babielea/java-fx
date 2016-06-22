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

    /**
     * Startet die PDF Generierung
     *
     * @param lernfelder   Lernfelder
     * @param dataForCover Deckblatt Daten
     * @throws IOException
     * @throws DocumentException
     */
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
        GenerateDetail(doc, lernfelder);

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

    /**
     * Generiert den HTML Code für die Jahresübersicht
     *
     * @param document   Document
     * @param lernfelder Alle Lernfelder
     * @throws IOException
     */
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
                if (!previousFach.equalsIgnoreCase(lernfeld.getFach())) {
                    stringBuilder.append(GetHTMLCode(lernfeld.getFach(), 12, "background-color:lightgreen"));
                    previousFach = lernfeld.getFach();
                }
            }

            String contentLernfeld = String.format("LF %s: %s (%d UStd)", lernfeld.getLernfeldNumber(),
                    lernfeld.getDescription(),
                    lernfeld.getLernfeldDuration());
            String htmlCodeLernfeld = GetHTMLCode(contentLernfeld, 12, "");
            stringBuilder.append(htmlCodeLernfeld);

            //Lernsituationen
            for (Lernsituation lernsituation : lernfeld.getLernsituationen()) {
                System.out.println(String.format("LS %s.%s: %s (%s UStd)", lernfeld.getLernfeldNumber(),
                        lernsituation.getLernstationsNo(),
                        lernsituation.getName(),
                        lernsituation.getuStunden()));
                String contentLernsituation = String.format("LS %s.%s: %s (%s UStd)", lernfeld.getLernfeldNumber(),
                        lernsituation.getLernstationsNo(),
                        lernsituation.getName(),
                        lernsituation.getuStunden());
                String htmlCodeLernsituation = GetHTMLCode(contentLernsituation, lernsituation.getBis(), "");
                stringBuilder.append(htmlCodeLernsituation);
            }
        }

        content = content.replace(Placeholder.FOREACH_LERNSITUATION, stringBuilder.toString());

        htmlWorker.parse(new StringReader(content));
    }

    /**
     * Generiert die DetailÜbersicht aus der Datenbank
     *
     * @param document  Document
     * @param lernfelds Lernfelder
     * @throws IOException
     */
    public void GenerateDetail(Document document, List<Lernfeld> lernfelds) throws IOException {
        HTMLWorker htmlWorker = new HTMLWorker(document);
        String content = fileManager.getFullFileContent(TEMPLATE_DETAILS);
        StringBuilder stringBuilder = new StringBuilder();

        //Placeholder ersetzen
        for (Lernfeld lernfeld : lernfelds) {
            for (Lernsituation lernsituation : lernfeld.getLernsituationen()) {
                System.out.println(lernsituation.getName());
                stringBuilder.append("<table style=\"width:100%\" border=\"1\">");

                stringBuilder.append(GetHTMLCode(String.format("<b>Fach:</b> %s", lernfeld.getFach()), 12, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>Lernfeld</b> %s", lernfeld.getLernfeldNumber(), lernfeld.getDescription()), 12, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>Lernsituation %d:</b> %s", lernsituation.getLernstationsNo(), lernfeld.getDescription()), 9, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>Dauer:</b> %d", lernsituation.getuStunden()), 2, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>ID:</b> %d", lernsituation.getLsID()), 1, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>Erstellt von:</b> %s<br /><b>Erstellt am:</b> %s", lernsituation.getErsteller(), lernsituation.getCreted()), 12, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>Einstiegsszenario:</b> %s", lernsituation.getSzenario()), 12, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>Handlungsprodukt/Lernergebnis:</b> %s", lernsituation.getHandlungsprodukt()), 12, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>Wesentliche Kopetenzen:</b> %s", lernsituation.getKompetenzen()), 12, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>Inhalt:</b> %s", lernsituation.getInhalt()), 12, ""));
                stringBuilder.append(GetHTMLCode(String.format("<b>Unterrichtsmaterialien:</b> %s <br /><b>Organisatorische Hinweise:</b> %s", lernsituation.getuMaterial(), lernsituation.getOrganisation()), 12, ""));

                stringBuilder.append("</table><br /><br />");
            }
        }

        content = content.replace(Placeholder.FOREACH_LERNSTATIONEN, stringBuilder.toString());
        htmlWorker.parse(new StringReader(content));
    }

    /**
     * Generiert den HTML Code für die Platzhalter
     *
     * @param value   Inhalt der Zelle
     * @param colspan Spaltenspannweite
     * @param style   HTML Style
     * @return
     */
    private String GetHTMLCode(String value, int colspan, String style) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<tr>");

        String ret = String.format("<td colspan=\"%d\" style=\"%s\">%s</td>", colspan, style, value);
        stringBuilder.append(ret);

        stringBuilder.append("</tr>");
        return stringBuilder.toString();
    }
}
