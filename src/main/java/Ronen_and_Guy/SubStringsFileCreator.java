package Ronen_and_Guy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

// Implement a string alignment algorithm: you are given a large reference string (10s-100s of millions of characters) named X,
// and you are given N (N > |X| / 10) strings of length 100, where each of those strings represents a substring of X of unknown location.
// Implement an algorithm that finds each string's location in a performant manner.
// Give details about its space and time complexity.
// Improve your initial implementation, using benchmarks to prove your changes made an improvement and giving technical explanations on why the changes improved your performance.

public class SubStringsFileCreator {
    public static final String CreatedFileName = "SubStringsFile.txt";
    private static final String gaintTextFile = GiantTextFileCreator.CreatedFileName;

    public static void main(String... args) {
        try {
            long start = System.currentTimeMillis();
            Random random = new Random();
            File textFile = new File(gaintTextFile);
            final long charactersNumber = textFile.length();
            final long minCharacterNumber = charactersNumber/10;
            final int charactersNumberRange = (int)(charactersNumber - minCharacterNumber);
            final long numberOfSubStrings = random.nextInt(charactersNumberRange) + minCharacterNumber;

            // TODO: read and select sub strings

            File subStringsFile = new File(CreatedFileName);
            subStringsFile.createNewFile();
            FileWriter writer = new FileWriter(subStringsFile);

            // TODO: write sub strings to file

            writer.flush();
            writer.close();
            long end = System.currentTimeMillis();
            System.out.println((end - start) / 1000f + " seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
