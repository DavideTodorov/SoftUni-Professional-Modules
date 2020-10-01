import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        Stream<String> lines = reader.lines();
        List<String> collect = lines.collect(Collectors.toList());
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (String s : collect) {
            writer.write(s.toUpperCase());
            writer.write("\n");
        }

        writer.close();
    }
}
