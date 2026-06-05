package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.Mutation;
import org.htw.prog2.aufgabe1.files.MutationFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//implementiert das Interface MutationFileReader, ist eine Refaktorisierung der Einlesemethoden
// aus MutationPatterns aus der letzten Woche

//liest eine CSV-Datei mit Mutationsmustern ein, erstellt daraus Mutation-Objekte
//und speichert sie in einem MutationFile-Objekt

public class CSVFileReader implements MutationFileReader{
    @Override
    public MutationFile readFile(String filename) throws IOException, FileFormatException {
        MutationFile mutationfile = new MutationFile();

        //zum Speichern der Spaltenzahl der Definitionszeile
        int defelements = 0;

        List<String> drugs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line = br.readLine();

            //Wenn die erste Zeile mit # beginnt, wird die nächste Zeile eingelesen
            if (line.startsWith("#")) {
                line = br.readLine();

                if (line == null) {
                    throw new FileFormatException("First line of mutation pattern CSV file must be a header");
                }

                try {
                    //Erstellt aus einer line mit der Methode parseDrugs eine Medikamenten-Liste
                    drugs = parseDrugs(line);
                } catch (FileFormatException e) {
                    throw new FileFormatException("First line of mutation pattern CSV file must be a header");
                }
                //Speichert Anzahl an Elementen/Spalten in der Definitionszeile
                String[] defparts = line.split(";");
                defelements = defparts.length;

                while ((line = br.readLine()) != null) {
                    //Leere Zeichen werden übersprungen
                    if (line.trim().equals("")) {
                        continue;
                    }
                    //Zeilen, die mit # beginnen werden übersprungen
                    if (line.startsWith("#")) {
                        continue;
                    }

                    //Trennt Zeile in Elemente und prüft, ob Definitionszeile und andere Zeile gleich viele Elemente enthalten
                    String[] zparts = line.split(";");

                    if (defelements != zparts.length) {
                        throw new FileFormatException("All lines in a CSV file must have the same number of elements");
                    }
                    //Speichert Mutationsmuster/erstes Element der Zeile (Beispiel:"9M")
                    String variant = zparts[0];

                    //HashMap: key: Name des Medikaments als String, value: Veränderung der Wirksamkeit dieses Medikaments
                    HashMap<String, Double> resistances = new HashMap<>();

                    //Fügt mit einer Schleife die Medikamente aus der Medikamenten-Liste
                    //und die Veränderung der Wirksamkeit aus der Zeile der HashMap hinzu
                    //Die Einträge der Medikamente beginnen in der Zeile an Stelle 2
                    // während die drugs-Liste bei 0 beginnt
                    for (int i = 2; i < zparts.length; i++) {
                        String drug = drugs.get(i - 2);
                        //Setzt NBSP-Zeichen auf 0, so dass sie zu einem Double
                        // geparsed werden können
                        if(zparts[i].equals("\u00A0")){
                            zparts[i] = "0";
                        }
                        //String-Zahlen werden in double umgewandelt
                        double value = Double.parseDouble(zparts[i]);
                        //Medikamente und Veränderung der Wirksamkeit des Medikaments werden in HashMap eingetragen
                        resistances.put(drug, value);
                    }
                    //Erstellt neues Mutation-Objekt mit Variantendefinition und HashMap mit Medikament und Wirkungswert
                    //fügt es dem Mutationfile hinzu
                    Mutation m = new Mutation(variant, resistances);
                    mutationfile.addMutation(m);
                }
            }

            return mutationfile;
        }
    }

    //prüft, ob Datei vom CSVFileReader gelesen werden kann
    @Override
    public boolean canReadFile(String filename){
        //System.out.println("CSV canReadFile: " + filename);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            line = br.readLine();
            if (line != null && line.startsWith("#")) {
                return true;}
            else {return false;}
        }catch (IOException e){
            return false;
        }
    }

    public static List<String> parseDrugs(String line) throws FileFormatException {
        List<String> drugs = new ArrayList<>();

        String[] parts = line.split(";");

        if (!parts[0].equals("\"Mutation Patterns\"")){
            throw new FileFormatException("");
        }
        if(!parts[1].equals("\"Number of Sequences\"")){
            throw new FileFormatException("");
        }

        for(int i = 2; i < parts.length; i++){
            if(!parts[i].endsWith(" foldn\"")){
                throw new FileFormatException("");
            }
            else {
                //schneidet bei den Medikamentennamen die Anführungszeichen vorne und hinten und das foldn ab
                parts[i] = parts[i].substring(1, parts[i].length() - 7);
                drugs.add(parts[i]);
            }
        }
        return drugs;
    }
}
