package Ronen_and_Guy;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
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
        createFile();
    }

    public static void createFile(){
        try {
            long start = System.currentTimeMillis();

            // Read the giant file
            char[] longString = GiantTextFileCreator.getLongStringFromFile();

            // declare the number of sub strings
            //final int minCharacterNumber = charactersNumber/10;
            //final int charactersNumberRange = (charactersNumber - minCharacterNumber);
            //final int numberOfSubStrings = random.nextInt(charactersNumberRange) + minCharacterNumber;
            final int numberOfSubStrings = 1000;

            // Write sub-strings to file
            File subStringsFile = new File(CreatedFileName);
            subStringsFile.createNewFile();
            BufferedWriter  writer = new BufferedWriter(new FileWriter(subStringsFile));

            for (int i=0; i<numberOfSubStrings; i++) {
                char[] subString = getRandomSubString(longString);
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

    private static char[] getRandomSubString(char[] longString){
        Random random = new Random();
        final int charactersNumber = longString.length;
        final int maxIndexOfPossibleSubString = charactersNumber - subStringLength;
        int indexOfSubString = random.nextInt(maxIndexOfPossibleSubString);
        char[] subString = Arrays.copyOfRange(longString, indexOfSubString, indexOfSubString + subStringLength);
        return subString;
    }

    public static class SubStringsIterator implements Iterator{
        BufferedReader br;
        boolean hasNext = true;

        public SubStringsIterator() {
            try {
                br = new BufferedReader(new FileReader(CreatedFileName));
            } catch (FileNotFoundException e) {
                createFile();
                try {
                    br = new BufferedReader(new FileReader(CreatedFileName));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public char[] next() {
            try{
                String line = br.readLine();

                if(line == null){
                    hasNext = false;
                    return null;
                }else{
                    return line.toCharArray();
                }
            } catch (Exception e) {
                return null;
            }
        }
    }
}
