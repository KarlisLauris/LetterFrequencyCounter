import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;


public class LetterFrequencyCounterTest {

    @Test
    @SneakyThrows
    public void testLetterFrequencyCounter() {
        String inputString = "Hello World";

        Map<Character, Integer> expectedFrequenciesMap = Map.of(
                'h', 1,
                'e', 1,
                'l', 3,
                'o', 2,
                'w', 1,
                'r', 1,
                'd', 1
        );
        TreeMap<Character, Integer> sortedFrequenciesMap = new TreeMap<>(expectedFrequenciesMap);
        setupTestFile(inputString, sortedFrequenciesMap);


        String actualOutputString = Files.readString(Path.of("src/main/resources/output.txt"));
        String tempOutputString = Files.readString(Path.of("src/main/resources/testOutput.txt"));
        Assertions.assertEquals(tempOutputString, actualOutputString);
    }

    @Test
    @SneakyThrows
    public void testLetterFrequencyCounterWithEmptyString() {
        String inputString = "";
        TreeMap<Character, Integer> sortedFrequenciesMap = new TreeMap<>();
        setupTestFile(inputString, sortedFrequenciesMap);
        String actualOutputString = Files.readString(Path.of("src/main/resources/output.txt"));
        String tempOutputString = Files.readString(Path.of("src/main/resources/testOutput.txt"));
        Assertions.assertEquals(tempOutputString, actualOutputString);
    }

    @Test
    @SneakyThrows
    public void testLetterFrequencyCounterWithNullString() {
        String inputString = null;
        TreeMap<Character, Integer> sortedFrequenciesMap = new TreeMap<>();
        setupTestFile(inputString, sortedFrequenciesMap);
        String actualOutputString = Files.readString(Path.of("src/main/resources/output.txt"));
        String tempOutputString = Files.readString(Path.of("src/main/resources/testOutput.txt"));
        Assertions.assertEquals(tempOutputString, actualOutputString);
    }

    @Test
    @SneakyThrows
    public void testLetterFrequencyCounterWithSpecialCharacters() {
        String inputString = "Hello World!@#$%^&*()_+";
        Map<Character, Integer> expectedFrequenciesMap = Map.of(
                'h', 1,
                'e', 1,
                'l', 3,
                'o', 2,
                'w', 1,
                'r', 1,
                'd', 1
        );
        TreeMap<Character, Integer> sortedFrequenciesMap = new TreeMap<>(expectedFrequenciesMap);
        setupTestFile(inputString, sortedFrequenciesMap);
        String actualOutputString = Files.readString(Path.of("src/main/resources/output.txt"));
        String tempOutputString = Files.readString(Path.of("src/main/resources/testOutput.txt"));
        Assertions.assertEquals(tempOutputString, actualOutputString);
    }

    @Test
    @SneakyThrows
    public void testLetterFrequencyCounterWithNumbers() {
        String inputString = "Hello World1234567890";
        Map<Character, Integer> expectedFrequenciesMap = Map.of(
                'h', 1,
                'e', 1,
                'l', 3,
                'o', 2,
                'w', 1,
                'r', 1,
                'd', 1
        );
        TreeMap<Character, Integer> sortedFrequenciesMap = new TreeMap<>(expectedFrequenciesMap);
        setupTestFile(inputString, sortedFrequenciesMap);
        String actualOutputString = Files.readString(Path.of("src/main/resources/output.txt"));
        String tempOutputString = Files.readString(Path.of("src/main/resources/testOutput.txt"));
        Assertions.assertEquals(tempOutputString, actualOutputString);
    }

    @Test
    @SneakyThrows
    public void testLetterFrequencyWithNonEnglishLetters() {
        String inputString = "Hello Worldāčēģīķļņšūž";
        Map<Character, Integer> expectedFrequenciesMap = Map.of(
                'h', 1,
                'e', 1,
                'l', 3,
                'o', 2,
                'w', 1,
                'r', 1,
                'd', 1
        );
        TreeMap<Character, Integer> sortedFrequenciesMap = new TreeMap<>(expectedFrequenciesMap);
        setupTestFile(inputString, sortedFrequenciesMap);
        String actualOutputString = Files.readString(Path.of("src/main/resources/output.txt"));
        String tempOutputString = Files.readString(Path.of("src/main/resources/testOutput.txt"));
        Assertions.assertEquals(tempOutputString, actualOutputString);
    }

    @AfterEach
    @SneakyThrows
    public void tearDown() {
        Files.deleteIfExists(Path.of("src/main/resources/testInput.txt"));
        Files.deleteIfExists(Path.of("src/main/resources/testOutput.txt"));
        Files.deleteIfExists(Path.of("src/main/resources/output.txt"));
    }

    @SneakyThrows
    private void setupTestFile(String inputString, TreeMap<Character, Integer> sortedFrequenciesMap) {
        String testInputPath = "testInput.txt";
        BufferedWriter writer = Files.newBufferedWriter(Path.of("src/main/resources", testInputPath));
        writer.write(Objects.requireNonNullElse(inputString, ""));
        writer.close();

        LetterFrequencyCounter.inputName = testInputPath;
        BufferedWriter writer2 = Files.newBufferedWriter(Path.of("src/main/resources", "testOutput.txt"));
        for (Map.Entry<Character, Integer> entry : sortedFrequenciesMap.entrySet()) {
            writer2.write(entry.getKey() + " " + entry.getValue());
            writer2.newLine();
        }
        writer2.close();
        InputStream in = new ByteArrayInputStream(testInputPath.getBytes());
        System.setIn(in);
        LetterFrequencyCounter.main(null);
    }
}
