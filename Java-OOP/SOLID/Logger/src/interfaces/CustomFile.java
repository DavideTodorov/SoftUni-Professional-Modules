package interfaces;

public interface CustomFile {
    void write(String text);

    void setFile(CustomFile customFile);

    long getSize();
}
