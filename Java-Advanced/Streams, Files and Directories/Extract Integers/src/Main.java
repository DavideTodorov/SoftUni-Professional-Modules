import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedWriter outputIntegers = new BufferedWriter(new FileWriter("output.txt"));

        try {
            Scanner text = new Scanner(new FileReader("input.txt"));

            while (text.hasNext()) {
                if (text.hasNextInt()) {
                    outputIntegers.write(String.valueOf(text.nextInt()));
                    outputIntegers.write("\n");
                }
                text.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        outputIntegers.close();

    }
}
