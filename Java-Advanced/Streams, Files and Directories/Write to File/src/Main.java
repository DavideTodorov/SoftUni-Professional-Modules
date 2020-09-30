import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Character> toRemove = new ArrayList<>();
        toRemove.add(',');
        toRemove.add('.');
        toRemove.add('!');
        toRemove.add('?');

        FileOutputStream outputText = new FileOutputStream("output.txt");
        try {
            FileInputStream text = new FileInputStream("input.txt");
            int oneByte = text.read();
            while (oneByte != -1) {
                if (!toRemove.contains((char) oneByte)) {
                    outputText.write(oneByte);
                }
                oneByte = text.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        outputText.close();
    }
}
