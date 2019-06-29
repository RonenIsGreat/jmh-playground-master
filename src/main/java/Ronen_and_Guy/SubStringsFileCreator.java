package Ronen_and_Guy;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

// Implement a string alignment algorithm: you are given a large reference string (10s-100s of millions of characters) named X,
// and you are given N (N > |X| / 10) strings of length 100, where each of those strings represents a substring of X of unknown location.
// Implement an algorithm that finds each string's location in a performant manner.
// Give details about its space and time complexity.
// Improve your initial implementation, using benchmarks to prove your changes made an improvement and giving technical explanations on why the changes improved your performance.

public class SubStringsFileCreator {
    public static final String CreatedFileName = "SubStringsFile.txt";
    private static final int subStringLength = 100;

    public static void main(String... args) {
        try {
            long start = System.currentTimeMillis();

            // Read the giant file
            char[] longString = GiantTextFileCreator.ReadLongStringFromFile();

            // Get the sub-strings
            char[][] subStrings = CreateRandomSubStrings(longString);

            // Write sub-strings to file
            File subStringsFile = new File(CreatedFileName);
            subStringsFile.createNewFile();
            BufferedWriter  writer = new BufferedWriter(new FileWriter(subStringsFile));
            for (char[] subString : subStrings) {
                writer.write(subString);
                writer.newLine();
            }
            writer.flush();
            writer.close();

            long end = System.currentTimeMillis();
            System.out.println((end - start) / 1000f + " seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static char[][] CreateRandomSubStrings(char[] longString){
        Random random = new Random();
        final int charactersNumber = longString.length;
        final int minCharacterNumber = charactersNumber/10;
        final int charactersNumberRange = (charactersNumber - minCharacterNumber);
        final int numberOfSubStrings = random.nextInt(charactersNumberRange) + minCharacterNumber;
        final int maxIndexOfPossibleSubString = charactersNumber - subStringLength;
        char[][] subStrings = new char[numberOfSubStrings][];

        for (int i=0; i<subStrings.length; i++){
            int indexOfSubString = random.nextInt(maxIndexOfPossibleSubString);
            char[] subString = Arrays.copyOfRange(longString, indexOfSubString, indexOfSubString + subStringLength);
            subStrings[i] = subString;
        }

        return subStrings;
    }
}
