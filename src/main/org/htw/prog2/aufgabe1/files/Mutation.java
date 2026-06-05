package org.htw.prog2.aufgabe1.files;

import java.util.HashMap;

public class Mutation {

    //Constructor, der eine Variantendefinition (siehe erste Woche, z.B. "9M") sowie
    // eine Hashmap mit einer Beschreibung der Auswirkungen
    // (key: Name des Medikaments als String, value: Veränderung der Wirksamkeit dieses Medikaments) nimmt.

    private String variant;
    private HashMap<String, Double> resistances;

    public Mutation(String variant, HashMap<String, Double> resistances){
    this.variant = variant;
    this.resistances = resistances;

    }

    //Getter für die Variante
    public String getVariant(){
        return this.variant;
    }

    //Getter für die Resistenzen
    public HashMap<String, Double> getResistances(){
        return this.resistances;
    }
}
