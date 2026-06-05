package org.htw.prog2.aufgabe1.files;

import java.util.LinkedList;

public class MutationFile implements HIVFile {

    LinkedList<String> drugs = new LinkedList<>();
    LinkedList<Mutation> mutations = new LinkedList<>();

    //soll neben einem leeren Constructor die folgenden Methoden
    // (z.T. refaktorisiert aus MutationPatterns der letzten Woche) enthalten:

    public MutationFile() {

    }
    //Fügt der Liste der gespeicherten Medikamente ein neues Medikament hinzu
    public void addDrug(String drug){
        drugs.add(drug);
    }

    //Gibt die gespeicherten Medikamentnamen zurück
    public LinkedList<String> getDrugs(){
        return drugs;
    }

    //Fügt zur Liste der gespeicherten Mutationen eine neue Mutation hinzu
    public void addMutation(Mutation variant){
        mutations.add(variant);
    }

    //Gibt die gespeicherten Mutationen zurück
    public LinkedList<Mutation> getMutations(){
        return mutations;
    }

    //Gibt die Anzahl der gespeicherten Mutationen zurück
    public int getNumberOfMutations(){
        return getMutations().size();
    }
}
