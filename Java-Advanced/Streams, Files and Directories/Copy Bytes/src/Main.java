import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        FileWriter outputText = new FileWriter("output.txt");

        try {
            FileReader text = new FileReader("input.txt");

            int oneByte = text.read();

            while (oneByte != -1) {

                if ((char) oneByte == ' ') {
                    outputText.write(' ');
                } else if ((char) oneByte == '\n') {
                    outputText.write('\n');
                } else {
                    outputText.write(String.valueOf(oneByte));
                }

                oneByte = text.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        outputText.close();
    }
}
