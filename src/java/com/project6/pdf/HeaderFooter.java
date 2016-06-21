package com.project6.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RottsiK on 21.06.2016.
 */
public class HeaderFooter extends PdfPageEventHelper {
    Phrase[] header = new Phrase[2];
    private int pagenumber;

    /*public void onOpenDocument(PdfWriter pdfWriter, Document document) {
        header[0] = new Phrase("Test122");
    }*/

    public void onChapter(PdfWriter writer, Document document,
                          float paragraphPosition, Paragraph title) {
        header[1] = new Phrase(title.getContent());
        pagenumber = 1;
    }

    /**
     * Increase the page number.
     */
    public void onStartPage(PdfWriter writer, Document document) {
        pagenumber++;
    }

    /**
     * Adds the header and the footer.
     */
    public void onEndPage(PdfWriter writer, Document document) {
        Image logo = null;
        try {
            logo = Image.getInstance("C:\\Users\\rottsik\\Documents\\IdeaProjects\\java-fx\\src\\resources\\image\\logo_kleint.png");
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        float t = logo.getAbsoluteX();
        Chunk chunk = new Chunk(logo, 0, 0);

        Rectangle rect = writer.getBoxSize("headFooter");
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_RIGHT, new Phrase(chunk),
                rect.getRight(), rect.getTop(), 0);

        //Mitte Seitenanzahl
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_CENTER, new Phrase(String.format("Seite %d", pagenumber)),
                (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);

        //Links aktuelles Datum
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, new Phrase(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date())),
                rect.getLeft(), rect.getBottom() - 18, 0);
    }
}
