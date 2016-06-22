package com.project6.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.org.apache.bcel.internal.util.ClassLoader;

import javax.print.attribute.standard.PagesPerMinute;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RottsiK on 21.06.2016.
 */
public class HeaderFooter extends PdfPageEventHelper {
    /**
     * Seitenanzahl
     */
    private int pagenumber;

    /**
     * oStartPage Event, erstellen des Headers
     *
     * @param writer   PdfWriter
     * @param document Document
     */
    public void onStartPage(PdfWriter writer, Document document) {

        //TODO: Wenn Zeit ist, dann Logo in Header
        pagenumber++;

        /*Rectangle rect = writer.getBoxSize("headFooter");
        Image logo = null;
        try {
            logo = Image.getInstance("C:\\Users\\rottsik\\Documents\\IdeaProjects\\java-fx\\src\\resources\\image\\logo_kleint.png");
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase(new Chunk(logo, 0, 0, true)), rect.getRight(), rect.getTop(),0);*/
    }

    /**
     * onEndPage Event, erstellen der Footer
     *
     * @param writer   PdfWriter
     * @param document Document
     */
    public void onEndPage(PdfWriter writer, Document document) {
        Rectangle rect = writer.getBoxSize("headFooter");
//        ColumnText.showTextAligned(writer.getDirectContent(),
//                Element.ALIGN_RIGHT, new Phrase(chunk),
//                rect.getRight(), rect.getTop(), 0);

        //Wenn true => Dokument ist im Hochformat
        //Wenn false => Dokument ist im Querformat
        if (document.getPageSize().getHeight() == PageSize.A4.getHeight()) {
            //Mitte Seitenanzahl
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase(String.format("Seite %d", pagenumber)),
                    (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 25, 0);
        } else {
            //Mitte Seitenanzahl
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase(String.format("Seite %d", pagenumber)),
                    (rect.getLeft() + rect.getRight() - 175), rect.getBottom() - 25, 0);
        }

        //Links aktuelles Datum
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, new Phrase(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date())),
                rect.getLeft(), rect.getBottom() - 25, 0);
    }
}
