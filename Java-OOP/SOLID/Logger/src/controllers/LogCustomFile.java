package controllers;

import interfaces.CustomFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LogCustomFile implements CustomFile {
    private String filePath;
    private File file;
    private long size;
    private PrintWriter writer;

    public LogCustomFile() {
        this.filePath = "out.txt";
        this.file = new File(filePath);
        this.size = 0;
        try {
            this.writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String text) {
        this.addToSize(text);
        writer.write(text);
        writer.write(System.lineSeparator());
        writer.flush();
    }

    private void addToSize(String text) {
        long toAdd = 0;
        for (int i = 0; i < text.length(); i++) {
            char currChar = text.charAt(i);

            if ((currChar >= 65 && currChar <= 90) || (currChar >= 97 && currChar <= 122)) {
                toAdd += currChar;
            }
        }

        this.size += toAdd;
    }

    @Override
    public void setFile(CustomFile customFile) {
        this.file = file;
    }

    @Override
    public long getSize() {
        return this.size;
    }
}
