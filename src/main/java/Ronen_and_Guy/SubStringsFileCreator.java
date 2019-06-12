package Ronen_and_Guy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SubStringsFileCreator {
    static final String createdFileName = "SubStringsFile.txt";
    static final String sourceFileName = "GiantFile.txt";

    public static void main(String... args) {
        try {
            long start = System.currentTimeMillis();

            // TODO: read and select sub strings

            File file = new File(createdFileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

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
