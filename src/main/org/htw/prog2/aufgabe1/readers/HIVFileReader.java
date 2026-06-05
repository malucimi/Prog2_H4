package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.HIVFile;

import java.io.IOException;

public interface HIVFileReader {
    //Diese Methode soll die übergebene Datei einlesen und wie SequenceFile Exceptions werfen,
    //wenn die Datei fehlt oder das Format falsch ist
    public HIVFile readFile(String filename) throws IOException, FileFormatException;

    //Gibt true zurück, wenn die Datei von diesem Reader
    //(basierend auf einem ersten "Reingucken") gelesen werden kann, sonst false
    public boolean canReadFile(String filename);

}