package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FASTAFileReader implements SequenceFileReader{


    @Override
    public SequenceFile readFile(String filename) throws IOException, FileFormatException {
        SequenceFile fastafile = new SequenceFile();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line1;
            line1 = br.readLine();

            if (!line1.startsWith(">")) {
                throw new FileFormatException("FASTA File does not start with sequence header line.");
            }
            String line;
            String lastline = null;

            while ((line = br.readLine()) != null) {

                if (line.trim().equals("")) {
                    continue;
                }

                lastline = line;

                if (line1.startsWith(">") && line.startsWith(">")) {
                    throw new FileFormatException("Two header lines are directly following each other.");
                }
                if (!line.startsWith(">")) {
                    fastafile.addSequence(line);
                }

                line1 = line;

            }
            if (lastline.startsWith(">")) {
                throw new FileFormatException("The last line is a sequence header.");
            }
        }
        return fastafile;
    }

    public boolean canReadFile(String filename){
        //System.out.println("FASTA canReadFile: " + filename);
        try{BufferedReader br = new BufferedReader(new FileReader(filename));
            String line1;
            line1 = br.readLine();
            if (line1 != null && line1.startsWith(">")) {
            return true;}
            else {return false;}
        }catch (IOException e){
            return false;
        }
    }
}
