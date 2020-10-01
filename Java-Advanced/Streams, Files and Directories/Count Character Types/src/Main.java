import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        String vowels = "aeiou";
        String punctuation = "!,.?";

        int vowelsCount = 0;
        int consonantsCount = 0;
        int punctuationCount = 0;
        String lineInput = reader.readLine();

        while (lineInput != null) {

            for (char c : lineInput.toCharArray()) {
                if (vowels.contains(String.valueOf(c))) {
                    vowelsCount++;
                } else if (punctuation.contains(String.valueOf(c))) {
                    punctuationCount++;
                } else if (c != ' ') {
                    consonantsCount++;
                }
            }
            lineInput = reader.readLine();
        }

        reader.close();

        String vowelsToPrint = String.format("Vowels: %d\n", vowelsCount);
        String consonantsToPrint = String.format("Consonants: %d\n", consonantsCount);
        String punctuationToPrint = String.format("Punctuation: %d\n", punctuationCount);

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write(vowelsToPrint);
        writer.write(consonantsToPrint);
        writer.write(punctuationToPrint);

        writer.close();
    }
}
