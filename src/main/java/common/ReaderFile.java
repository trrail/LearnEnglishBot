package common;

import java.io.*;
import java.util.ArrayList;

public class ReaderFile {
    private String path;

    public ReaderFile(String path){
        this.path = path;
    }

    public ArrayList<String> readLines(){
        ArrayList<String> lines = new ArrayList<>();
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public void setPath(String path){
        this.path = path;
    }
}
