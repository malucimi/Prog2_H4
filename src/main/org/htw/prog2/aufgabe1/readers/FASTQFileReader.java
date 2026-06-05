package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Liest FASTQ-File ein und gibt ein SequenceFile-Objekt mit Sequenzzeilen zurück

public class FASTQFileReader implements SequenceFileReader{
    @Override
    public SequenceFile readFile (String filename)throws IOException, FileFormatException{
        SequenceFile fastaqfile = new SequenceFile();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))){

            String header = br.readLine();

            if (header == null || !header.startsWith("@")) {
                throw new FileFormatException("FASTQ File does not start with sequence header line.");
            }

            while (header != null) {
                String sequence = br.readLine();
                fastaqfile.addSequence(sequence);
                String trennzeile = br.readLine();
                String quality = br.readLine();
                header = br.readLine();

            }
        }
        return fastaqfile;
        }

        public boolean canReadFile(String filename){
            //System.out.println("FASTQ canReadFile: " + filename);
            try{BufferedReader br = new BufferedReader(new FileReader(filename));
                    String line1;
                    line1 = br.readLine();
                    if (line1 != null && line1.startsWith("@")) {
                        return true;}
                    else {return false;}
                }catch (IOException e){
                    return false;
                }
        }

}
