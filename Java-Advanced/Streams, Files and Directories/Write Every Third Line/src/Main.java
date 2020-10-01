import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        Stream<String> lines = reader.lines();
        List<String> linesList = lines.collect(Collectors.toList());

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        for (int i = 2; i < linesList.size(); i += 3) {
            writer.write(linesList.get(i));
            writer.write("\n");
        }

        writer.close();
        reader.close();
    }
}
