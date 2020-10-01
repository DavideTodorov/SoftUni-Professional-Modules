import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        //Reader
        BufferedReader wordsList = new BufferedReader(new FileReader("words.txt"));
        Stream<String> wordsLines = wordsList.lines();
        List<String> wordsCollection = wordsLines.collect(Collectors.toList());
        wordsList.close();

        Set<String> words = new LinkedHashSet<>();

        for (String line : wordsCollection) {
            String[] currArr = line.split("[,.!? ]");
            words.addAll(Arrays.asList(currArr));
        }

        //Writer
        BufferedReader text = new BufferedReader(new FileReader("text.txt"));
        Stream<String> textLines = text.lines();
        List<String> textLinesCollection = textLines.collect(Collectors.toList());
        text.close();

        Map<String, Integer> wordsCount = new LinkedHashMap<>();

        for (String s : textLinesCollection) {
            String[] wordsOnLine = s.split("[,.!? ]");
            for (String currWord : wordsOnLine) {
                if (words.contains(currWord)) {
                    wordsCount.putIfAbsent(currWord, 0);
                    wordsCount.put(currWord, wordsCount.get(currWord) + 1);
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        for (Map.Entry<String, Integer> wordAndCount : wordsCount.entrySet()) {
            String formattedString = String.format("%s - %d\n",
                    wordAndCount.getKey(),
                    wordAndCount.getValue());
            writer.write(formattedString);
        }

        writer.close();
    }
}
