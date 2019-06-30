package Ronen_and_Guy;

import java.io.*;
import java.util.Random;

// Implement a string alignment algorithm: you are given a large reference string (10s-100s of millions of characters) named X,
// and you are given N (N > |X| / 10) strings of length 100, where each of those strings represents a substring of X of unknown location.
// Implement an algorithm that finds each string's location in a performant manner.
// Give details about its space and time complexity.
// Improve your initial implementation, using benchmarks to prove your changes made an improvement and giving technical explanations on why the changes improved your performance.

public class GiantTextFileCreator {
    public static final String CreatedFileName = "GiantFile.txt";
    private static final int minCharactersNumber = 10000000;
    private static final int maxCharactersNumber = 100000000;

    public static void main(String... args) {
        try {
            long start = System.currentTimeMillis();

            // The long string
            char[] characters = createRandomLongString();

            // Write the long string to file
            File file = new File(CreatedFileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(characters);
            writer.flush();
            writer.close();

            long end = System.currentTimeMillis();
            System.out.println((end - start) / 1000f + " seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static char[] createRandomLongString(){
        Random random = new Random();
        final int charactersNumberRange = maxCharactersNumber - minCharactersNumber;
        final int charactersNumber = random.nextInt(charactersNumberRange) + minCharactersNumber;
        char[] characters = new char[charactersNumber];

        for (int i = 0; i < charactersNumber; i++) {
            // a-z letters
            char c = (char)(random.nextInt(26) + 'a');
            characters[i] = c;
        }

        return characters;
    }

    public static char[] readLongStringFromFile() throws IOException {
        File textFile = new File(CreatedFileName);
        FileReader fileReader = new FileReader(textFile);
        int fileSize = (int) textFile.length();
        char[] longString = new char[fileSize];
        fileReader.read(longString);
        fileReader.close();
        return longString;
    }
}
