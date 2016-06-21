package com.project6.pdf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by RottsiK on 21.06.2016.
 */
public class FileManager {

    /**
     * Liest den Inhalt aus einer Datei aus.
     *
     * @param filename Dateipfad der zu lesenden Datei
     * @return Datei Inhalt
     * @throws IOException
     * @throws IllegalArgumentException Dateiname ist NULL oder leer
     */
    public List<String> readFile(String filename) throws IOException, IllegalArgumentException {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException("Der Dateiname darf nicht NULL oder leer sein.");

        if (!new File(filename).exists())
            throw new IllegalArgumentException("Die angegebene Datei konnte nicht gefunden werden.");

        return Files.readAllLines(Paths.get(filename), StandardCharsets.ISO_8859_1);
    }

    /**
     * Schreibt den Inhalt in eine Datei.
     *
     * @param filename Dateipfad der zu schreibenden Datei
     * @param text     Inhalt der Datei
     * @throws IOException
     * @throws IllegalArgumentException Dateiname ist NULL oder leer
     */
    public void writeFile(String filename, String text) throws IOException, IllegalArgumentException {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException("Der Dateiname darf nicht NULL oder leer sein.");

        File file = new File(filename);

        if (!file.exists())
            file.createNewFile();


        try (FileWriter fileWriter = new FileWriter(file))
        {
            fileWriter.write(text);
        }
    }
}
