package org.htw.prog2.aufgabe1.files;

import java.util.HashSet;
import java.util.LinkedHashSet;

//die die Informationen eines Sequenz-files halten wird und die von HIVFile ableitet.
// Sie soll neben einem leeren Constructor die folgenden Methoden
// (z.T. refaktorisiert aus SeqFile der letzten Woche) enthalten:

public class SequenceFile implements HIVFile {
    //Hier sollen alle Sequenzen in der Einlese-Reihenfolge gespeichert werden
    LinkedHashSet<String> linkedSequences = new LinkedHashSet<>();

    public SequenceFile() {
    }

    //Fügt eine neue Sequenz der Sequenzliste hinzu
    public void addSequence(String sequence){
        linkedSequences.add(sequence);
    }

    //Gibt die gespeicherten Sequenzen zurück
    public HashSet<String> getSequences(){
        //LinkedHashSet wird in gefordertes, unsortiertes HashSet umgewandelt
        HashSet<String> sequences = new HashSet<>(linkedSequences);
        return sequences;
    }

    //Gibt die erste hinzugefügte Sequenz zurück
    public String getFirstSequence(){
        String firstSequence = linkedSequences.iterator().next();
        return firstSequence;
    }

    //Gibt die Anzahl der gespeicherten Sequenzen zurück
    public int getNumberOfSequences(){
        return linkedSequences.size();
    }
}


