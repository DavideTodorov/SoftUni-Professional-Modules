package controllers;

import interfaces.CustomFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LogCustomFile implements CustomFile {
    private String filePath;
    private File file;
    private long size;

    public LogCustomFile() {
        this.filePath = "out.txt";
        this.file = new File(filePath);
        this.size = 0;
    }

    @Override
    public void write(String text) {
        this.addToSize(text);

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(text);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addToSize(String text) {
        long toAdd = 0;
        for (int i = 0; i < text.length(); i++) {
            toAdd += text.charAt(i);
        }
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
