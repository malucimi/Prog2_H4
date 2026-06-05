package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.NoValidReadersException;

import java.util.ArrayList;
import java.util.List;

public class ReaderManager<T extends HIVFileReader>{
    private List<T> readers = new ArrayList<>();

    // Fügt der Liste der bekannten Reader einen neuen Reader hinzu
    public void addReader(T reader){
        readers.add(reader);
    }
    //Findet in der Liste der hinzugefügten Reader einen, der die Datei filename einlesen kann
    // (denken Sie an die Methode canReadFile(String filename), die von HIVFileReader vorgeschrieben wird)
    // und gibt diesen Reader zurück. Falls kein passender Reader gefunden werden konnte,
    // wird eine org.htw.prog2.aufgabe1.exceptions.NoValidReadersException geworfen
    public T getReaderForFile(String filename)throws NoValidReadersException {
        for(T reader : readers){
            //System.out.println("Teste " + reader.getClass().getSimpleName() + " für " + filename);
            if(reader.canReadFile(filename)){
                return reader;
            }
        }
        throw new NoValidReadersException("");
    }

}

