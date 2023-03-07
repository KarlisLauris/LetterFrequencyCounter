import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

@Log
public class LetterFrequencyCounter {
    public static String inputName = null;
    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        log.info("Enter input file name: ");
        inputName = scanner.nextLine();
        String outputPath = "output.txt";
        InputStream inputText = Paths.get("src/main/resources", inputName).toUri().toURL().openStream();
        assert inputText != null;
        TreeMap<Character, Integer> sortedFrequenciesMap = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputText));
        BufferedWriter writer = Files.newBufferedWriter(Path.of("src/main/resources", outputPath));
        String line;
        while ((line = reader.readLine()) != null) {
            line =  line.replaceAll("[^a-zA-Z]", "").toLowerCase();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                sortedFrequenciesMap.put(c, sortedFrequenciesMap.getOrDefault(c, 0) + 1);
            }
        }
        reader.close();

        for (Map.Entry<Character, Integer> entry : sortedFrequenciesMap.entrySet()) {
            writer.write(entry.getKey() + " " + entry.getValue());
            writer.newLine();
        }
        writer.close();
    }
}
